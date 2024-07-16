package com.kekulta.events.presentation.ui.widgets.base.animation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimation(
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

