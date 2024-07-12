package com.kekulta.events.ui.widgets


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.kekulta.events.ui.theme.EventsTheme

const val CODE_LENGTH = 4

@Composable
fun CodeField(
    modifier: Modifier = Modifier,
    state: TextFieldState = rememberTextFieldState(),
    onDone: (TextFieldState) -> Unit = {},
    length: Int = CODE_LENGTH,
    enabled: Boolean = true,
) {
    val focusManager = LocalFocusManager.current

    BasicTextField(
        state = state,
        inputTransformation = InputTransformation.maxLength(length),
        modifier = modifier.fillMaxWidth(),
        lineLimits = TextFieldLineLimits.SingleLine,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onKeyboardAction = {
            focusManager.clearFocus()
            onDone(state)
        },
        decorator = { _ ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(length) { index ->
                    val currentChar = state.text.getOrNull(index)

                    AnimatedContent(
                        modifier = Modifier.weight(1f),
                        targetState = currentChar,
                        transitionSpec = {
                            /*
                                It is technically possible to choose here animation, so digit always
                                come and go from and to the top.
                                It would look much nicer, like we do in NavBar.
                                But I can't figure out how to deal with bug, that if you delete char
                                before animation ended thw wrong animation will be played.
                             */
                            (slideInVertically { height -> height } + fadeIn() togetherWith slideOutVertically { height -> -height } + fadeOut()) using SizeTransform(
                                clip = false
                            )

                        },
                        label = "Code animation"
                    ) { currChar ->
                        Box(
                            modifier = Modifier.height(measureRenderedHeight(style = EventsTheme.typography.heading1)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (currChar == null) {
                                Box(
                                    modifier = Modifier
                                        .size(EventsTheme.sizes.sizeX12)
                                        .clip(CircleShape)
                                        .background(
                                            EventsTheme.colors.neutralLine
                                        )
                                )
                            } else {
                                Text(
                                    text = currChar.toString(),
                                    textAlign = TextAlign.Center,
                                    style = EventsTheme.typography.heading1,
                                )
                            }
                        }
                    }
                }
            }
        },
    )
}

@Composable
fun measureRenderedHeight(style: TextStyle): Dp {
    val textMeasurer = rememberTextMeasurer()
    val singleLineHeightPx = remember(textMeasurer, style) {
        textMeasurer.measure("0", style).size.height
    }
    val singleLineHeightDp = with(LocalDensity.current) {
        singleLineHeightPx.toDp()
    }

    return singleLineHeightDp
}
