package com.kekulta.events.ui.base.ripple

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

@Composable
fun FullAlphaRipple(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides FullAlphaRipple) {
        content()
    }
}

@Composable
fun PressOnlyRipple(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides PressOnlyRipple) {
        content()
    }
}

@Composable
fun ContentRipple(
    contentColor: Color,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalRippleTheme provides ContentRipple,
        LocalContentColor provides contentColor
    ) {
        content()
    }
}

@Immutable
object FullAlphaRipple : RippleTheme {
    @Composable
    override fun defaultColor() = LocalContentColor.current

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(1f, 1f, 1f, 1f)
}

@Immutable
object PressOnlyRipple : RippleTheme {
    @Composable
    override fun defaultColor() = LocalContentColor.current

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0f, 0f, 0f, 0.12f)
}

@Immutable
object ContentRipple : RippleTheme {
    @Composable
    override fun defaultColor() = LocalContentColor.current

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0f, 0f, 0f, 0.4f)
}

fun Modifier.clickableWithRipple(color: Color, onClick: () -> Unit): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(color = color),
        onClick = onClick
    )
}

