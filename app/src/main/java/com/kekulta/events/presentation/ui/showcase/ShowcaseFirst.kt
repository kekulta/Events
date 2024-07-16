package com.kekulta.events.presentation.ui.showcase

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventSquareAvatar
import com.kekulta.events.presentation.ui.widgets.EventsSearchField
import com.kekulta.events.presentation.ui.widgets.UserCircleAddAvatar
import com.kekulta.events.presentation.ui.widgets.UserCircleAvatar
import com.kekulta.events.presentation.ui.widgets.UserSquareAvatar
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsButtonDefaults
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsOutlinedButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsTextButton
import com.kekulta.events.presentation.ui.widgets.base.chips.RoundChip
import com.kekulta.events.presentation.ui.widgets.base.snackbar.SnackbarScope
import kotlinx.coroutines.launch

fun LazyListScope.showcaseFirst(
    snackbarScope: SnackbarScope,
    focusedSource: MutableInteractionSource,
    hoveredSource: MutableInteractionSource,
    isEnabled: Boolean,
) {
    item {
        TempSpacer()
        TextGroup()
    }
    item {
        TempSpacer()
        ChipsGroup()
    }
    item {
        TempSpacer()
        SearchGroup(snackbarScope)
    }
    item {
        TempSpacer()
        MeetAvatarsGroup(snackbarScope)
    }
    item {
        TempSpacer()
        AvatarsGroup(snackbarScope)
    }

    item {
        TempSpacer()
        InteractionSwitcher(focusedSource = focusedSource, hoveredSource = hoveredSource)
    }

    item {
        TempSpacer()
        FilledButtonsGroup(
            focusedSource = focusedSource, hoveredSource = hoveredSource, enabled = isEnabled
        )
    }

    item {
        TempSpacer()
        OutlinedButtonsGroup(
            focusedSource = focusedSource, hoveredSource = hoveredSource, enabled = isEnabled
        )
    }

    item {
        TempSpacer()
        TextButtonsGroup(
            focusedSource = focusedSource, hoveredSource = hoveredSource, enabled = isEnabled
        )
    }
    item {
        TempSpacer()
        IconsButtonGroup(focusedSource = focusedSource, hoveredSource = hoveredSource)
    }
}


@Composable
fun TempSpacer() {
    Spacer(
        modifier = Modifier
            .width(20.dp)
            .height(20.dp)
    )
}

@Composable
fun SearchGroup(
    snackbarScope: SnackbarScope,
) {
    Row {
        EventsSearchField(state = rememberTextFieldState(),
            enabled = true,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            onSearch = {
                snackbarScope {
                    showSnackbar("Searching: ${it.text}")
                }
            })
    }
}

@Composable
fun InteractionSwitcher(
    focusedSource: MutableInteractionSource,
    hoveredSource: MutableInteractionSource,
) {
    val scope = rememberCoroutineScope()

    val focusInteraction = remember {
        FocusInteraction.Focus()
    }
    val hoverInteraction = remember {
        HoverInteraction.Enter()
    }

    var isFocus by remember { mutableStateOf(true) }
    var isHover by remember { mutableStateOf(true) }

    LaunchedEffect(focusedSource, hoveredSource) {
        scope.launch {
            focusedSource.emit(focusInteraction)
            hoveredSource.emit(hoverInteraction)
        }
    }

    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Change focus value",
                modifier = Modifier
                    .padding(ButtonDefaults.ContentPadding)
                    .weight(1f, true)
            )
            Switch(checked = isFocus,
                modifier = Modifier.padding(ButtonDefaults.ContentPadding),
                onCheckedChange = {
                    scope.launch {
                        if (isFocus) {
                            focusedSource.emit(FocusInteraction.Unfocus(focusInteraction))
                        } else {
                            focusedSource.emit(focusInteraction)
                        }
                        isFocus = !isFocus
                    }
                })
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Change hover value",
                modifier = Modifier
                    .padding(ButtonDefaults.ContentPadding)
                    .weight(1f, true)
            )
            Switch(checked = isHover,
                modifier = Modifier.padding(ButtonDefaults.ContentPadding),
                onCheckedChange = {
                    scope.launch {
                        if (isHover) {
                            hoveredSource.emit(HoverInteraction.Exit(hoverInteraction))
                        } else {
                            hoveredSource.emit(hoverInteraction)
                        }
                        isHover = !isHover
                    }
                })
        }
    }
}

@Composable
fun TextButtonsGroup(
    focusedSource: MutableInteractionSource,
    hoveredSource: MutableInteractionSource,
    enabled: Boolean,
) {

    EventsTextButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {}) {
        Text(text = "Normal Button")
    }
    EventsTextButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {},
        interactionSource = hoveredSource,
    ) {
        Text(text = "Hovered Button")
    }
    EventsTextButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {},
        interactionSource = focusedSource,
    ) {
        Text(text = "Focused Button")
    }

    EventsTextButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {},
        enabled = enabled,
    ) {
        Text(text = "Disabled Button")
    }
}

@Composable
fun OutlinedButtonsGroup(
    focusedSource: MutableInteractionSource,
    hoveredSource: MutableInteractionSource,
    enabled: Boolean,
) {
    EventsOutlinedButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {}) {
        Text(text = "Normal Button")
    }
    EventsOutlinedButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {},
        interactionSource = hoveredSource,
    ) {
        Text(text = "Hovered Button")
    }
    EventsOutlinedButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {},
        interactionSource = focusedSource,
    ) {
        Text(text = "Focused Button")
    }

    EventsOutlinedButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {},
        enabled = enabled,
    ) {
        Text(text = "Disabled Button")
    }
}

@Composable
fun FilledButtonsGroup(
    focusedSource: MutableInteractionSource,
    hoveredSource: MutableInteractionSource,
    enabled: Boolean,
) {
    EventsFilledButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {}) {
        Text(text = "Normal Button")
    }
    EventsFilledButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {},
        interactionSource = hoveredSource,
    ) {
        Text(text = "Hovered Button")
    }
    EventsFilledButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {},
        interactionSource = focusedSource,
    ) {
        Text(text = "Focused Button")
    }

    EventsFilledButton(
        modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX10)
            .fillMaxWidth(),
        onClick = {},
        enabled = enabled,
    ) {
        Text(text = "Disabled Button")
    }
}

@Composable
fun IconsButtonGroup(
    focusedSource: MutableInteractionSource,
    hoveredSource: MutableInteractionSource,
) {
    EventsOutlinedButton(
        onClick = {},
        contentPadding = EventsButtonDefaults.iconPaddingDefaults(),
    ) {
        Icon(
            modifier = Modifier.size(EventsTheme.sizes.sizeX10),
            painter = painterResource(id = R.drawable.icon_delete),
            contentDescription = "Delete icon"
        )
    }
}

@Composable
fun MeetAvatarsGroup(snackbarScope: SnackbarScope) {
    EventSquareAvatar(
        url = "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4",
    )
    TempSpacer()
    EventSquareAvatar()
}

@Composable
fun AvatarsGroup(snackbarScope: SnackbarScope) {
    val scope = rememberCoroutineScope()

    UserSquareAvatar(
        url = "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4",
        drawBorder = true,
    )
    TempSpacer()
    UserSquareAvatar(
    )
    TempSpacer()
    UserCircleAvatar(
        url = "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4",
    )
    TempSpacer()
    UserCircleAvatar()
    TempSpacer()
    UserCircleAddAvatar(
        url = "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4",
        onBadgeClick = { snackbarScope { showSnackbar("Change avatar") } }
    )
    TempSpacer()
    UserCircleAddAvatar(
        onBadgeClick = { snackbarScope { showSnackbar("Change avatar") } }
    )
}

@Composable
fun ChipsGroup() {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        RoundChip(text = "Python")
        RoundChip(text = "Junior")
        RoundChip(text = "Moscow")
    }
}

@Composable
fun TextGroup() {
    @Composable
    fun TextShowcase(name: String, font: String, style: TextStyle) {
        Row {
            Column(
                Modifier.weight(0.4f)
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = name,
                    style = EventsTheme.typography.subheading1,
                    color = EventsTheme.colors.neutralActive
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp),
                    text = font,
                    style = EventsTheme.typography.bodyText2,
                    color = EventsTheme.colors.neutralDisabled
                )
            }
            Text(
                modifier = Modifier.weight(0.6f),
                text = "The quick brown fox jumps over the lazy dog",
                style = style
            )
        }
    }

    Column {
        TextShowcase(
            name = "Heading 1",
            font = "SF Pro Display/32/Bold",
            style = EventsTheme.typography.heading1
        )
        TempSpacer()
        TextShowcase(
            name = "Heading 2",
            font = "SF Pro Display24/Bold",
            style = EventsTheme.typography.heading2
        )
        TempSpacer()
        TextShowcase(
            name = "Subheading 1",
            font = "SF Pro Display18/SemiBold",
            style = EventsTheme.typography.subheading1
        )
        TempSpacer()
        TextShowcase(
            name = "Subheading 2",
            font = "SF Pro Display16/SemiBold",
            style = EventsTheme.typography.subheading2
        )
        TempSpacer()
        TextShowcase(
            name = "Body Text 1",
            font = "SF Pro Display/14/SemiBold",
            style = EventsTheme.typography.bodyText1
        )
        TempSpacer()
        TextShowcase(
            name = "Body Text 2",
            font = "SF Pro Display14/Regular",
            style = EventsTheme.typography.bodyText2
        )
        TempSpacer()
        TextShowcase(
            name = "Metadata 1",
            font = "SF Pro Display12/Regular",
            style = EventsTheme.typography.metadata1
        )
        TempSpacer()
        TextShowcase(
            name = "Metadata 2",
            font = "SF Pro Display10/Regular",
            style = EventsTheme.typography.metadata2
        )
        TempSpacer()
        TextShowcase(
            name = "Metadata 3",
            font = "SF Pro Display12/SemiBold",
            style = EventsTheme.typography.metadata3
        )
    }
}