package com.kekulta.events.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute


val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("No navigator provided!")
}

@Composable
fun findNavigator(): Navigator = LocalNavigator.current

@Composable
fun ProvideNavigator(navController: NavController, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalNavigator provides NavComponentNavigator(navController),
        content = content
    )
}

inline fun <reified T : Screen> NavGraphBuilder.screen(
    state: MutableState<NavigationState>,
    noinline screenAction: @Composable (() -> Unit)? = null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T> { navBackStackEntry ->
        state.value = state.value.copy(
            screen = navBackStackEntry.toRoute<T>(),
            action = screenAction,
        )
        content(navBackStackEntry)
    }
}
