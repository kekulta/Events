package com.kekulta.events.ui.base.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kekulta.events.ui.base.ripple.PressOnlyRipple
import com.kekulta.events.ui.theme.EventsTheme


@Composable
fun EventsButton(
    onClick: () -> Unit,
    colors: EventsButtonColorStateList,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    val hovered = interactionSource.collectIsHoveredAsState()
    val focused = interactionSource.collectIsFocusedAsState()


    val containerColor = statedColor(
        color = colors.container,
        enabled = enabled,
        hovered = hovered.value,
        focused = focused.value
    )

    val contentColor = statedColor(
        color = colors.content, enabled = enabled, hovered = hovered.value, focused = focused.value
    )

    val borderColor = statedColor(
        color = colors.border, enabled = enabled, hovered = hovered.value, focused = focused.value
    )
    PressOnlyRipple {
        Box(
            modifier = modifier
                .defaultMinSize(
                    minHeight = ButtonDefaults.MinHeight,
                    minWidth = ButtonDefaults.MinWidth,
                )
                .clip(ButtonDefaults.shape)
                .focusBorder(
                    width = 8.dp,
                    color = colors.focusBorder,
                    shape = ButtonDefaults.shape, interactionSource = interactionSource,
                )
                .padding(PaddingValues(8.dp))
                .clip(ButtonDefaults.shape)
                .background(containerColor.value)
                .border(BorderStroke(1.5.dp, borderColor.value), ButtonDefaults.shape),
            propagateMinConstraints = true
        ) {
            CompositionLocalProvider(LocalContentColor provides contentColor.value) {
                ProvideTextStyle(value = EventsTheme.typography.subheading2) {
                    Row(
                        Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = rememberRipple(),
                                onClick = onClick,
                                enabled = enabled,
                            )
                            .defaultMinSize(
                                minHeight = 48.dp,
                            )
                            .padding(contentPadding),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        content = content
                    )
                }
            }
        }
    }
}

@Composable
fun EventsFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    EventsButton(
        onClick = onClick,
        colors = EventsButtonDefaults.filledDefaults(),
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
fun EventsTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    EventsButton(
        onClick = onClick,
        colors = EventsButtonDefaults.textDefaults(),
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
fun EventsOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    EventsButton(
        onClick = onClick,
        colors = EventsButtonDefaults.outlinedDefaults(),
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}
