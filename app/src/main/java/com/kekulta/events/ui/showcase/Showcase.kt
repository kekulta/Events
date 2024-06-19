package com.kekulta.events.ui.showcase

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kekulta.events.R
import com.kekulta.events.ui.avatar.AddBadge
import com.kekulta.events.ui.avatar.Avatar
import com.kekulta.events.ui.avatar.MeetAvatar
import com.kekulta.events.ui.buttons.EventsButtonDefaults
import com.kekulta.events.ui.buttons.EventsFilledButton
import com.kekulta.events.ui.buttons.EventsOutlinedButton
import com.kekulta.events.ui.buttons.EventsTextButton
import com.kekulta.events.ui.chips.RoundChip
import com.kekulta.events.ui.search.SearchField
import com.kekulta.events.ui.theme.EventsTheme
import kotlinx.coroutines.launch

@Composable
fun Showcase(
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    val isHighContrast = remember {
        mutableStateOf(false)
    }
    val background by rememberUpdatedState(if (isHighContrast.value) Color.Black else Color.White)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(background)
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {
        val focusedSource = remember { MutableInteractionSource() }
        val hoveredSource = remember { MutableInteractionSource() }

        val isEnabled = remember {
            mutableStateOf(false)
        }


        TempSpacer()
        TextGroup()
        TempSpacer()
        ChipsGroup()
        TempSpacer()
        SearchGroup(snackbarHostState = snackbarHostState)
        TempSpacer()
        MeetAvatarsGroup(snackbarHostState = snackbarHostState)
        TempSpacer()
        AvatarsGroup(snackbarHostState = snackbarHostState)

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Enable contrast background",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(ButtonDefaults.ContentPadding)
                    .weight(1f, true)
            )
            Switch(checked = isHighContrast.value,
                modifier = Modifier.padding(ButtonDefaults.ContentPadding),
                onCheckedChange = { isHighContrast.value = !isHighContrast.value })
        }

        TempSpacer()
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Change enabled value",
                modifier = Modifier
                    .padding(ButtonDefaults.ContentPadding)
                    .weight(1f, true)
            )
            Switch(checked = isEnabled.value,
                modifier = Modifier.padding(ButtonDefaults.ContentPadding),
                onCheckedChange = { isEnabled.value = !isEnabled.value })
        }

        InteractionSwitcher(focusedSource = focusedSource, hoveredSource = hoveredSource)
        TempSpacer()
        FilledButtonsGroup(
            focusedSource = focusedSource, hoveredSource = hoveredSource, enabled = isEnabled.value
        )
        TempSpacer()
        OutlinedButtonsGroup(
            focusedSource = focusedSource, hoveredSource = hoveredSource, enabled = isEnabled.value
        )
        TempSpacer()
        TextButtonsGroup(
            focusedSource = focusedSource, hoveredSource = hoveredSource, enabled = isEnabled.value
        )
        TempSpacer()
        IconsButtonGroup(focusedSource = focusedSource, hoveredSource = hoveredSource)
    }
}


@Composable
fun TempSpacer() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchGroup(
    snackbarHostState: SnackbarHostState,
) {
    val scope = rememberCoroutineScope()

    SearchField(state = rememberTextFieldState(),
        enabled = true,
        modifier = Modifier.padding(12.dp),
        onSearch = {
            scope.launch {
                snackbarHostState.showSnackbar("Searching: ${it.text}")
            }
        })

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

    val isFocus = remember { mutableStateOf(true) }
    val isHover = remember { mutableStateOf(true) }

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
            Switch(checked = isFocus.value,
                modifier = Modifier.padding(ButtonDefaults.ContentPadding),
                onCheckedChange = {
                    scope.launch {
                        if (isFocus.value) {
                            focusedSource.emit(FocusInteraction.Unfocus(focusInteraction))
                        } else {
                            focusedSource.emit(focusInteraction)
                        }
                        isFocus.value = !isFocus.value
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
            Switch(checked = isHover.value,
                modifier = Modifier.padding(ButtonDefaults.ContentPadding),
                onCheckedChange = {
                    scope.launch {
                        if (isHover.value) {
                            hoveredSource.emit(HoverInteraction.Exit(hoverInteraction))
                        } else {
                            hoveredSource.emit(hoverInteraction)
                        }
                        isHover.value = !isHover.value
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

    EventsTextButton(onClick = {}) {
        Text(text = "Normal Button")
    }
    EventsTextButton(
        onClick = {},
        interactionSource = hoveredSource,
    ) {
        Text(text = "Hovered Button")
    }
    EventsTextButton(
        onClick = {},
        interactionSource = focusedSource,
    ) {
        Text(text = "Focused Button")
    }

    EventsTextButton(
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
    EventsOutlinedButton(onClick = {}) {
        Text(text = "Normal Button")
    }
    EventsOutlinedButton(
        onClick = {},
        interactionSource = hoveredSource,
    ) {
        Text(text = "Hovered Button")
    }
    EventsOutlinedButton(
        onClick = {},
        interactionSource = focusedSource,
    ) {
        Text(text = "Focused Button")
    }

    EventsOutlinedButton(
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
    EventsFilledButton(onClick = {}) {
        Text(text = "Normal Button")
    }
    EventsFilledButton(
        onClick = {},
        interactionSource = hoveredSource,
    ) {
        Text(text = "Hovered Button")
    }
    EventsFilledButton(
        onClick = {},
        interactionSource = focusedSource,
    ) {
        Text(text = "Focused Button")
    }

    EventsFilledButton(
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
        contentPadding = EventsButtonDefaults.iconPadding(),
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.drawable.icon_delete),
            contentDescription = "Delete icon"
        )
    }
}

@Composable
fun MeetAvatarsGroup(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()

    MeetAvatar(
        url = "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4",
        badge = {
            AddBadge(modifier = Modifier.fillMaxSize(1f)) {
                scope.launch { snackbarHostState.showSnackbar("Change avatar") }
            }
        },
    )
    TempSpacer()
    MeetAvatar(
        badge = {
            AddBadge(modifier = Modifier.fillMaxSize(1f)) {
                scope.launch { snackbarHostState.showSnackbar("Change avatar") }
            }
        },
    )
    TempSpacer()
    MeetAvatar(
        url = "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4",
    )
    TempSpacer()
    MeetAvatar()
}

@Composable
fun AvatarsGroup(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()

    TempSpacer()
    Avatar(
        url = "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4",
        badge = {
            AddBadge {
                scope.launch { snackbarHostState.showSnackbar("Change avatar") }
            }
        },
    )
    TempSpacer()
    Avatar(
        badge = {
            AddBadge {
                scope.launch { snackbarHostState.showSnackbar("Change avatar") }
            }
        },
    )
    TempSpacer()
    Avatar(
        url = "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4",
    )
    TempSpacer()
    Avatar()
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