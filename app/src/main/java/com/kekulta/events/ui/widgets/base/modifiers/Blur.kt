package com.kekulta.events.ui.widgets.base.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.Dp

fun Modifier.blur(radius: Dp, enabled: Boolean): Modifier {
    return if (enabled) {
        blur(radius = radius)
    } else {
        this
    }
}
