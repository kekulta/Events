package com.kekulta.events.presentation.ui.widgets.base.modifiers

import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.insert
import androidx.compose.runtime.Stable

@Stable
data object PhoneNumberOutputTransformation : OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        if (length > 3) insert(3, " ")
        if (length > 7) insert(7, "-")
        if (length > 10) insert(10, "-")
    }
}