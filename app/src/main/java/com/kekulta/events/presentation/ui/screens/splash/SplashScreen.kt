package com.kekulta.events.presentation.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.navigation.Events
import com.kekulta.events.presentation.ui.navigation.requireNavigator
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.base.animation.LottieAnimation

@Composable
fun SplashScreen() {
    val navigator = requireNavigator()

    SetTopBar {
        EventsTopBarState(
            enabled = false,
            showBackButton = false,
            currScreenAction = null,
            currScreenName = "",
        )
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            modifier = Modifier.size(EventsTheme.sizes.sizeX100),
            LottieCompositionSpec.RawRes(
                R.raw.splash_coffee
            ),
            onEnd = { navigator.setRoot(Events()) },
        )
    }
}
