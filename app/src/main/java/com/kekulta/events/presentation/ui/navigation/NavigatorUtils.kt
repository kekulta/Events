package com.kekulta.events.presentation.ui.navigation

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.core.os.BundleCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.presentation.ui.update
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

/**
 * Returns [LocalOnBackPressedDispatcherOwner.current] or throws if it is empty.
 */
@Composable
fun requireOnBackPressDispatcher() = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
    "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
}.onBackPressedDispatcher

/**
 * Provides [Navigator]
 */
val LocalNavigator = staticCompositionLocalOf<Navigator?> {
    null
}

/**
 * Returns [LocalNavigator] or throws if no [Navigator] was provided.
 */
@Composable
fun requireNavigator(): Navigator = LocalNavigator.current
    ?: error("No navigator provided! Make sure that any Event's Custom Composable nested inside [ProvideNavigator]")

/**
 * Remembers EventsNavBarState.
 */
@Composable
fun rememberNavState(state: EventsNavBarState = EventsNavBarState(Tab.EVENTS)): MutableState<EventsNavBarState> =
    remember { mutableStateOf(state) }

/**
 * Provides [NavComponentNavigator] implementation of the [Navigator] to its children.
 */
@Composable
fun ProvideNavComponentNavigator(
    navController: NavController, onExit: () -> Unit, content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalNavigator provides NavComponentNavigator(navController, onExit), content = content
    )
}

/**
 * Adds composable destination to the NavGraph, that sets current Tab accordingly when navigated to.
 */
inline fun <reified T : Screen> NavGraphBuilder.screen(
    state: MutableState<EventsNavBarState>,
    slide: Boolean = false,
    noinline content: @Composable AnimatedContentScope.(Pair<NavBackStackEntry, T>) -> Unit
) {
    val slideInRight: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
        { slideInHorizontally { height -> height } + fadeIn() }

    val slideOutLeft: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
        { slideOutHorizontally { height -> -height } + fadeOut() }

    val slideInLeft: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
        { slideInHorizontally { height -> height } + fadeIn() }

    val slideOutRight: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
        { slideOutHorizontally { height -> -height } + fadeOut() }

    composable<T>(
        typeMap = mapOf(
            typeOf<Tab>() to parcelableNavArg<Tab>(),
            typeOf<EventId>() to stringNavArg<EventId>(
                encode = { id },
                decode = { value -> EventId(value) },
            ),
            typeOf<CommunityId>() to stringNavArg<CommunityId>(
                encode = { id },
                decode = { value -> CommunityId(value) },
            ),
            typeOf<UserId>() to stringNavArg<UserId>(
                encode = { id },
                decode = { value -> UserId(value) },
            ),
        ),
        enterTransition = slideInRight.takeIf { slide },
        exitTransition = slideOutLeft.takeIf { slide },
        popEnterTransition = slideInLeft.takeIf { slide },
        popExitTransition = slideOutRight.takeIf { slide },
    ) { navBackStackEntry ->
        val screen = navBackStackEntry.toRoute<T>()

        state.update { copy(tab = screen.tab) }

        content(Pair(navBackStackEntry, screen))
    }
}

/**
 * Creates custom [NavType] with provided [encode] and [decode] functions.
 * Intended for use with value classes or other easily serializable classes.
 */
inline fun <reified T> stringNavArg(
    crossinline encode: T.() -> String,
    crossinline decode: (value: String) -> T,
    isNullableAllowed: Boolean = false,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        decode(requireNotNull(bundle.getString(key)) { "Error while extracting NavArg!" })

    override fun parseValue(value: String): T = decode(Uri.decode(value))

    override fun serializeAsValue(value: T): String = Uri.encode(encode(value))

    override fun put(bundle: Bundle, key: String, value: T) = bundle.putString(key, encode(value))
}

/**
 * Creates custom [NavType] from provided [Parcelable] type.
 */
inline fun <reified T : Parcelable> parcelableNavArg(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        BundleCompat.getParcelable(bundle, key, T::class.java)

    override fun parseValue(value: String): T = json.decodeFromString(Uri.decode(value))

    override fun serializeAsValue(value: T): String = Uri.encode(json.encodeToString(value))

    override fun put(bundle: Bundle, key: String, value: T) = bundle.putParcelable(key, value)
}
