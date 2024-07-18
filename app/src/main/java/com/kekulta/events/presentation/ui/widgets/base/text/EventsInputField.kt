package com.kekulta.events.presentation.ui.widgets.base.text

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.base.buttons.focusBorder

@Composable
fun EventsInputField(
    modifier: Modifier = Modifier,
    state: TextFieldState = rememberTextFieldState(),
    onDone: (state: TextFieldState) -> Unit = {},
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    leadingIcon: Painter? = null,
    shouldDrawBorder: Boolean = true,
    hint: String = "",
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
) {
    val focusManager = LocalFocusManager.current

    /*
        We do *not*  want to recompose on every symbol change. So using derived state.
    */
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
            /*
                Does not support font size change!
             */
            .height(EventsTheme.sizes.sizeX18)
            .focusBorder(
                width = EventsTheme.sizes.sizeX1,
                color = EventsTheme.colors.neutralLine,
                shape = RoundedCornerShape(EventsTheme.sizes.sizeX2),
                enabled = isEmpty && shouldDrawBorder,
                interactionSource = interactionSource
            )
            .background(
                EventsTheme.colors.neutralOffWhite, RoundedCornerShape(EventsTheme.sizes.sizeX2)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        if (leadingIcon != null) {
            Icon(
                leadingIcon,
                "Input Icon",
                modifier = Modifier
                    .padding(start = EventsTheme.sizes.sizeX4)
                    .size(EventsTheme.sizes.sizeX12),
                tint = leadingIconTint,
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .padding(start = EventsTheme.sizes.sizeX4)
                .weight(1f, true),
        ) {
            Text(
                text = hint,
                style = EventsTheme.typography.bodyText1,
                color = hintColor,
            )

            BasicTextField(
                enabled = enabled,
                onKeyboardAction = {
                    onDone(state)
                    focusManager.clearFocus()
                },
                inputTransformation = inputTransformation,
                outputTransformation = outputTransformation,
                interactionSource = interactionSource,
                keyboardOptions = keyboardOptions,
                lineLimits = TextFieldLineLimits.SingleLine,
                textStyle = EventsTheme.typography.bodyText1.copy(color = EventsTheme.colors.neutralActive),
                state = state,
            )
        }
    }
}
