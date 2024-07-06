package com.kekulta.events.ui.navigation

import androidx.compose.runtime.Composable

data class NavigationState(
    val screen: Screen,
    val action: @Composable (() -> Unit)?,
)
