package com.kekulta.events.ui.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kekulta.events.ui.theme.EventsTheme

fun Modifier.focusBorder(
    width: Dp,
    color: Color,
    shape: Shape,
    enabled: Boolean = true,
    interactionSource: InteractionSource,
): Modifier = composed {
    val focus = interactionSource.collectIsFocusedAsState()
    if (focus.value && enabled) {
        this.border(width, color, shape)
    } else {
        this
    }
}


@Composable
fun statedColor(
    color: StatedColor, enabled: Boolean, hovered: Boolean, focused: Boolean
): State<Color> {
    return rememberUpdatedState(
        when {
            !enabled -> color.disabled
            focused -> color.focused
            hovered -> color.hovered
            else -> color.normal
        }
    )
}

@Immutable
data class EventsButtonColorStateList(
    val content: StatedColor,
    val container: StatedColor,
    val border: StatedColor,
    val focusBorder: Color,
)

@Immutable
data class StatedColor(
    val normal: Color,
    val disabled: Color = normal,
    val hovered: Color = normal,
    val focused: Color = normal,
)

object EventsButtonDefaults {
    @Composable
    fun filledDefaults(): EventsButtonColorStateList {
        return EventsButtonColorStateList(
            container = StatedColor(
                normal = EventsTheme.colors.brandDefault,
                disabled = EventsTheme.colors.brandDefault.copy(alpha = 0.5f),
                hovered = EventsTheme.colors.brandDark,
                focused = EventsTheme.colors.brandDefault,
            ),
            content = StatedColor(
                normal = EventsTheme.colors.neutralOffWhite
            ),
            border = StatedColor(
                normal = Color.Transparent,
            ),
            focusBorder = EventsTheme.colors.brandBackground,
        )
    }

    @Composable
    fun outlinedDefaults(): EventsButtonColorStateList {
        return EventsButtonColorStateList(
            container = StatedColor(
                normal = EventsTheme.colors.neutralWhite,
            ),
            content = StatedColor(
                normal = EventsTheme.colors.brandDefault,
                disabled = EventsTheme.colors.brandDefault.copy(alpha = 0.5f),
                hovered = EventsTheme.colors.brandDark,
                focused = EventsTheme.colors.brandDefault,
            ),
            border = StatedColor(
                normal = EventsTheme.colors.brandDefault,
                disabled = EventsTheme.colors.brandDefault.copy(alpha = 0.5f),
                hovered = EventsTheme.colors.brandDark,
                focused = EventsTheme.colors.brandDefault,
            ),
            focusBorder = EventsTheme.colors.neutralOffWhite,
        )
    }

    @Composable
    fun textDefaults(): EventsButtonColorStateList {
        return EventsButtonColorStateList(
            container = StatedColor(
                normal = Color.Transparent,
                focused = EventsTheme.colors.neutralLine,
            ),
            content = StatedColor(
                normal = EventsTheme.colors.brandDefault,
                disabled = EventsTheme.colors.brandDefault.copy(alpha = 0.5f),
                hovered = EventsTheme.colors.brandDark,
                focused = EventsTheme.colors.brandDefault,
            ),
            border = StatedColor(
                normal = Color.Transparent,
            ),
            focusBorder = EventsTheme.colors.neutralOffWhite,
        )
    }

    fun iconPadding() = PaddingValues(horizontal = 18.dp, vertical = 4.dp)
}

