package com.kekulta.events.ui.base.snackbar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

typealias SnackbarScope = (suspend SnackbarHostState.() -> Unit) -> Unit

@Composable
fun rememberSnackbarScope(
    snackbarHostState: SnackbarHostState,
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

fun SnackbarScope.showSnackbar(text: String) {
    this {
        showSnackbar(text)
    }
}