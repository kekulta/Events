package com.kekulta.events.presentation.ui.widgets.base.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

typealias SnackbarScope = (suspend SnackbarHostState.() -> Unit) -> Unit

val LocalSnackbarScope = staticCompositionLocalOf<SnackbarScope?> {
    null
}

@Composable
fun rememberSnackbarScope(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): SnackbarScope {
    return { snackbarScope ->
        coroutineScope.launch {
            snackbarHostState.apply {
                snackbarScope()
            }
        }
    }
}

fun SnackbarScope.showSnackbar(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = true,
    duration: SnackbarDuration =
        if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite
) {
    this {
        showSnackbar(message, actionLabel, withDismissAction, duration)
    }
}

@Composable
fun ProvideSnackbarScope(snackbarScope: SnackbarScope, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSnackbarScope provides snackbarScope) {
        content()
    }
}

@Composable
fun findSnackbarScope(): SnackbarScope? = LocalSnackbarScope.current


