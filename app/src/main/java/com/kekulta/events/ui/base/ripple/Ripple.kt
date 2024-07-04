package com.kekulta.events.ui.base.ripple

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentRipple(
    contentColor: Color,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalRippleConfiguration provides RippleConfiguration(
            color = contentColor,
            rippleAlpha = RippleAlpha(0f, 0f, 0f, 0.4f),
        ),
        LocalContentColor provides contentColor
    ) {
        content()
    }
}

fun Modifier.clickableWithRipple(color: Color, onClick: () -> Unit): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = ripple(color = color),
        onClick = onClick
    )
}

