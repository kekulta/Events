package com.kekulta.events.ui.elements

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.kekulta.events.R
import com.kekulta.events.ui.base.buttons.focusBorder
import com.kekulta.events.ui.theme.EventsTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    onSearch: (state: TextFieldState) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val isFocused by interactionSource.collectIsFocusedAsState()
    val shouldDrawBorder by rememberUpdatedState(isFocused && state.text.isEmpty())
    val hintColor by rememberUpdatedState(if (state.text.isEmpty()) EventsTheme.colors.neutralDisabled else Color.Transparent)
    val leadingIconTint by rememberUpdatedState(if (state.text.isEmpty()) EventsTheme.colors.neutralDisabled else EventsTheme.colors.neutralActive)

    Row(
        modifier = modifier
            .focusable(interactionSource = interactionSource)
            .hoverable(interactionSource = interactionSource)
            .padding(2.dp)
            .focusBorder(
                width = 2.dp,
                color = EventsTheme.colors.neutralLine,
                shape = RoundedCornerShape(4.dp),
                enabled = shouldDrawBorder,
                interactionSource = interactionSource
            )
            .fillMaxWidth()
            .height(36.dp)
            .background(EventsTheme.colors.neutralOffWhite, RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painterResource(id = R.drawable.icon_search),
            "Search Icon",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp),
            tint = leadingIconTint,
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.weight(1f, true),
        ) {
            Text(
                text = "Search",
                style = EventsTheme.typography.bodyText1,
                color = hintColor,
            )


            BasicTextField2(
                enabled = enabled,
                modifier = Modifier.fillMaxWidth(),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearch(state)
                    focusManager.clearFocus()
                }),
                interactionSource = interactionSource,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                lineLimits = TextFieldLineLimits.SingleLine,
                textStyle = EventsTheme.typography.bodyText1.copy(color = EventsTheme.colors.neutralActive),
                state = state,
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
    }
}
