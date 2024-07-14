package com.kekulta.events.ui.navigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedContentScope
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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Composable
fun getOnBackPressedDispatcher() = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
    "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
}.onBackPressedDispatcher

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("No navigator provided!")
}

@Composable
fun rememberNavState(): MutableState<EventsNavBarState> =
    remember { mutableStateOf(EventsNavBarState(Tab.EVENTS)) }

@Composable
fun findNavigator(): Navigator = LocalNavigator.current

@Composable
fun ProvideNavigator(navController: NavController, onExit: () -> Unit, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalNavigator provides NavComponentNavigator(navController, onExit), content = content
    )
}

inline fun <reified T : Screen> NavGraphBuilder.screen(
    state: MutableState<EventsNavBarState>,
    slide: Boolean = false,
    noinline content: @Composable AnimatedContentScope.(Pair<NavBackStackEntry, T>) -> Unit
) {
    composable<T>(
        typeMap = mapOf(typeOf<Tab>() to parcelableType<Tab>()),
        enterTransition = if (slide) {
            { slideInHorizontally { height -> height } + fadeIn() }
        } else {
            null
        },
        exitTransition = if (slide) {
            { slideOutHorizontally { height -> -height } + fadeOut() }
        } else {
            null
        },
        popEnterTransition = if (slide) {
            { slideInHorizontally { height -> -height } + fadeIn() }
        } else {
            null
        },
        popExitTransition = if (slide) {
            { slideOutHorizontally { height -> height } + fadeOut() }
        } else {
            null
        },
    ) { navBackStackEntry ->

        val screen = navBackStackEntry.toRoute<T>()

        state.value = state.value.copy(
            tab = screen.tab,
        )
        content(Pair(navBackStackEntry, screen))
    }
}

inline fun <reified T : Parcelable> parcelableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION") bundle.getParcelable(key)
        }

    override fun parseValue(value: String): T = json.decodeFromString(value)

    override fun serializeAsValue(value: T): String = json.encodeToString(value)

    override fun put(bundle: Bundle, key: String, value: T) = bundle.putParcelable(key, value)
}