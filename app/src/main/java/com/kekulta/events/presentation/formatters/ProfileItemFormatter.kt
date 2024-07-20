package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.presentation.ui.models.ProfileItemVo

class ProfileItemFormatter {
    fun format(model: ProfileModel): ProfileItemVo {
        return ProfileItemVo(
            // Append surname with prefixed with space, append nothing if its null
            name = model.info.name + (model.info.surname?.let { surname -> " $surname" } ?: ""),
            number = model.number.format(),
            avatar = model.info.avatar,
        )
    }
}