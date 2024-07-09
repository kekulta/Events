package com.kekulta.events.ui.screens.flows

import androidx.compose.runtime.Composable
import com.kekulta.events.ui.screens.splash.SplashScreen

@Composable
fun SplashFlow(
    onFlowEnd: () -> Unit,
) {
    SplashScreen(onFlowEnd)
}

