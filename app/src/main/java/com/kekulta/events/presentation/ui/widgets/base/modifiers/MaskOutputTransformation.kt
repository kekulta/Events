package com.kekulta.events.presentation.ui.widgets.base.modifiers

import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.insert
import androidx.compose.runtime.Stable

@Stable
data class MaskOutputTransformation(
    val mask: String,
    val replaceCharacter: Char = '#',
) : OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        var index = 0

        /*
            length changes as we insert characters, so use while
         */
        while (index < length) {
            if ((mask.getOrNull(index) ?: replaceCharacter) != replaceCharacter) {
                insert(index, mask[index].toString())
            }
            index++
        }
    }
}
