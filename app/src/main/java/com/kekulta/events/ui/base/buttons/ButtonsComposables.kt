package com.kekulta.events.ui.base.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.kekulta.events.ui.base.ripple.ContentRipple
import com.kekulta.events.ui.theme.EventsTheme


@Composable
fun EventsButton(
    onClick: () -> Unit,
    colors: EventsButtonColorStateList,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    debounceTime: Long = EventsButtonDefaults.defaultDebounceTime(),
    contentPadding: PaddingValues = EventsButtonDefaults.paddingDefaults(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    val hovered by interactionSource.collectIsHoveredAsState()
    val focused by interactionSource.collectIsFocusedAsState()

    val containerColor = statedColor(
        color = colors.container, enabled = enabled, hovered = hovered, focused = focused
    )

    val contentColor = statedColor(
        color = colors.content, enabled = enabled, hovered = hovered, focused = focused
    )

    val borderColor = statedColor(
        color = colors.border, enabled = enabled, hovered = hovered, focused = focused
    )
    ContentRipple(contentColor = contentColor) {
        Box(
            contentAlignment = Alignment.Center, modifier = modifier
                .defaultMinSize(
                    minHeight = ButtonDefaults.MinHeight,
                    minWidth = ButtonDefaults.MinWidth,
                )
                .clip(ButtonDefaults.shape)
                .focusBorder(
                    width = EventsTheme.sizes.sizeX4,
                    color = colors.focusBorder,
                    shape = ButtonDefaults.shape, interactionSource = interactionSource,
                )
                .padding(PaddingValues(EventsTheme.sizes.sizeX4))
                .clip(ButtonDefaults.shape)
                .debouncedClickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple(),
                    onClick = onClick,
                    debounceTime = debounceTime,
                    enabled = enabled,
                )
                .background(containerColor)
                .border(
                    BorderStroke(EventsTheme.sizes.sizeX1, borderColor), ButtonDefaults.shape
                ), propagateMinConstraints = true
        ) {
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                ProvideTextStyle(value = EventsTheme.typography.subheading2) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(contentPadding),
                        horizontalArrangement = Arrangement.Center,
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
    debounceTime: Long = EventsButtonDefaults.defaultDebounceTime(),
    contentPadding: PaddingValues = EventsButtonDefaults.paddingDefaults(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    EventsButton(
        onClick = onClick,
        colors = EventsButtonDefaults.filledColorsDefaults(),
        modifier = modifier,
        enabled = enabled,
        debounceTime = debounceTime,
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
    debounceTime: Long = EventsButtonDefaults.defaultDebounceTime(),
    contentPadding: PaddingValues = EventsButtonDefaults.paddingDefaults(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    EventsButton(
        onClick = onClick,
        colors = EventsButtonDefaults.textColorsDefaults(),
        modifier = modifier,
        enabled = enabled,
        debounceTime = debounceTime,
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
    debounceTime: Long = EventsButtonDefaults.defaultDebounceTime(),
    contentPadding: PaddingValues = EventsButtonDefaults.paddingDefaults(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    EventsButton(
        onClick = onClick,
        colors = EventsButtonDefaults.outlinedColorsDefaults(),
        modifier = modifier,
        enabled = enabled,
        debounceTime = debounceTime,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}
