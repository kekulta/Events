package com.kekulta.events.ui.screens.flows

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kekulta.events.R
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun SplashFlow(
    onFlowEnd: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedPreloader(
            modifier = Modifier.size(EventsTheme.sizes.sizeX100),
            LottieCompositionSpec.RawRes(
                R.raw.splash_coffee
            ),
            onEnd = onFlowEnd,
        )
    }
}

@Composable
fun AnimatedPreloader(
    modifier: Modifier = Modifier, spec: LottieCompositionSpec, onEnd: () -> Unit
) {
    var isEnded by remember {
        mutableStateOf(false)
    }

    val preloaderLottieComposition by rememberLottieComposition(
        spec
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition, iterations = 1, isPlaying = true
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = { preloaderProgress },
        modifier = modifier
    )

    if (preloaderProgress == 1f && !isEnded) {
        isEnded = true
        onEnd()
    }
}

