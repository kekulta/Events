package com.kekulta.events.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.kekulta.events.R
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.base.buttons.focusBorder

@Composable
fun SearchField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    /*
        Actually last argument shouldn't be a function parameter when it is not intended to  be
        used as trailing lambda a.k.a. a composable content. Should be fixed!
    */
    onSearch: (state: TextFieldState) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val isFocused by interactionSource.collectIsFocusedAsState()

    /*
        We do *not*  want to recompose on every symbol change. So using derived state.
    */
    val shouldDrawBorder by remember {
        derivedStateOf { isFocused && state.text.isEmpty() }
    }
    val isEmpty by remember {
        derivedStateOf { state.text.isEmpty() }
    }
    val hintColor = if (isEmpty) EventsTheme.colors.neutralDisabled else Color.Transparent
    val leadingIconTint =
        if (isEmpty) EventsTheme.colors.neutralDisabled else EventsTheme.colors.neutralActive

    Row(
        modifier = modifier
            .focusable(interactionSource = interactionSource)
            .hoverable(interactionSource = interactionSource)
            .height(EventsTheme.sizes.sizeX18)
            .focusBorder(
                width = EventsTheme.sizes.sizeX1,
                color = EventsTheme.colors.neutralLine,
                shape = RoundedCornerShape(EventsTheme.sizes.sizeX2),
                enabled = shouldDrawBorder,
                interactionSource = interactionSource
            )
            .background(
                EventsTheme.colors.neutralOffWhite, RoundedCornerShape(EventsTheme.sizes.sizeX2)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painterResource(id = R.drawable.icon_search),
            "Search Icon",
            modifier = Modifier
                .padding(start = EventsTheme.sizes.sizeX4)
                .size(EventsTheme.sizes.sizeX12),
            tint = leadingIconTint,
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .padding(start = EventsTheme.sizes.sizeX4)
                .weight(1f, true),
        ) {
            Text(
                text = "Search",
                style = EventsTheme.typography.bodyText1,
                color = hintColor,
            )


            BasicTextField(
                enabled = enabled,
                modifier = Modifier.fillMaxWidth(),
                onKeyboardAction = {
                    onSearch(state)
                    focusManager.clearFocus()
                },
                interactionSource = interactionSource,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                lineLimits = TextFieldLineLimits.SingleLine,
                textStyle = EventsTheme.typography.bodyText1.copy(color = EventsTheme.colors.neutralActive),
                state = state,
            )
        }
        Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX4))
    }
}
