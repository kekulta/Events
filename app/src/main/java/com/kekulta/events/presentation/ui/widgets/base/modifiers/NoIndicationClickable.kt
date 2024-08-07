package com.kekulta.events.presentation.ui.widgets.base.modifiers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.noIndicationClickable(
    interactionSource: MutableInteractionSource? = null, onClick: () -> Unit = {}
): Modifier = composed {
    clickable(
        interactionSource = interactionSource ?: remember {
            MutableInteractionSource()
        },
        onClick = onClick, indication = null
    )
}
