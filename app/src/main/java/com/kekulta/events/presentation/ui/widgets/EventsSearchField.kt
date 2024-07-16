package com.kekulta.events.presentation.ui.widgets

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.kekulta.events.R
import com.kekulta.events.presentation.ui.widgets.base.text.EventsInputField

@Composable
fun EventsSearchField(
    modifier: Modifier = Modifier,
    state: TextFieldState = rememberTextFieldState(),
    onSearch: (state: TextFieldState) -> Unit = {},
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
) {
    EventsInputField(
        modifier = modifier,
        state = state,
        onDone = onSearch,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        enabled = enabled,
        hint = "Search",
        leadingIcon = painterResource(id = R.drawable.icon_search),
        interactionSource = interactionSource,
    )
}

