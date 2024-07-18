package com.kekulta.events.presentation.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.base.buttons.focusBorder
import com.kekulta.events.presentation.ui.widgets.base.data.Countries
import com.kekulta.events.presentation.ui.widgets.base.data.Country
import com.kekulta.events.presentation.ui.widgets.base.modifiers.MaskOutputTransformation
import com.kekulta.events.presentation.ui.widgets.base.text.EventsInputField

const val PHONE_NUMBER_LENGTH = 10

@Composable
fun PhoneField(
    modifier: Modifier = Modifier,
    onDone: (TextFieldState, MutableState<Country>) -> Unit = { _, _ -> },
    shouldDrawBorder: Boolean = false,
    numberState: TextFieldState = rememberTextFieldState(),
    /*
        Not sure that this is good idea but it looks ok for now
     */
    countryState: MutableState<Country> = remember {
        mutableStateOf(Countries.russia)
    },
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
    val focusManager = LocalFocusManager.current
    var expanded by remember { mutableStateOf(false) }

    val isEmpty by remember {
        derivedStateOf { numberState.text.isEmpty() }
    }

    Row(modifier.fillMaxWidth()) {
        Column {
            Box(
                modifier = Modifier
                    .height(EventsTheme.sizes.sizeX18)
                    .clip(RoundedCornerShape(EventsTheme.sizes.sizeX2))
                    .clickable {
                        expanded = true
                    }

                    /*
                        Not sure if it should be here, but its easier to remove than to add.
                    */
                    .focusBorder(
                        width = EventsTheme.sizes.sizeX1,
                        color = EventsTheme.colors.neutralLine,
                        shape = RoundedCornerShape(EventsTheme.sizes.sizeX2),
                        enabled = isEmpty && shouldDrawBorder,
                        interactionSource = interactionSource
                    )
                    .background(EventsTheme.colors.neutralOffWhite)
                    .padding(
                        horizontal = EventsTheme.sizes.sizeX4,
                    ), contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(EventsTheme.sizes.sizeX4)
                ) {
                    Image(
                        modifier = Modifier.size(EventsTheme.sizes.sizeX8),
                        painter = painterResource(id = countryState.value.flagIcon),
                        contentDescription = null
                    )
                    Text(
                        text = countryState.value.countryCode,
                        style = EventsTheme.typography.bodyText1,
                        color = if (numberState.text.isNotEmpty()) EventsTheme.colors.neutralActive else EventsTheme.colors.neutralDisabled
                    )
                }
            }
            DropdownMenu(
                containerColor = EventsTheme.colors.neutralOffWhite,
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                Countries.countries.forEach { country ->
                    DropdownMenuItem(text = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(EventsTheme.sizes.sizeX4),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(EventsTheme.sizes.sizeX8),
                                painter = painterResource(id = country.flagIcon),
                                contentDescription = null
                            )
                            Text(
                                text = country.countryCode, color = EventsTheme.colors.neutralActive
                            )
                        }
                    }, onClick = {
                        countryState.value = country
                        expanded = false
                    })
                }
            }
        }

        Spacer(modifier = Modifier.width(EventsTheme.sizes.sizeX4))
        EventsInputField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            state = numberState,
            shouldDrawBorder = shouldDrawBorder,
            inputTransformation = InputTransformation.maxLength(PHONE_NUMBER_LENGTH),
            outputTransformation = MaskOutputTransformation("(###) ###-##-##"),
            hint = "000 000-00-00",
            onDone = {
                focusManager.clearFocus()
                onDone(numberState, countryState)
            },
            interactionSource = interactionSource,
        )
    }
}
