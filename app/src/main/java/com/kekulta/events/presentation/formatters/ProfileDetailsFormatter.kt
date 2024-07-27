package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.base.ProfileModel
import com.kekulta.events.presentation.ui.models.ProfileDetailsVo

class ProfileDetailsFormatter() {
    fun format(model: ProfileModel): ProfileDetailsVo {
        return ProfileDetailsVo(
            // Append surname with prefixed with space, append nothing if its null
            name = model.name + (model.surname?.let { surname -> " $surname" } ?: ""),
            number = model.identifier.number?.format() ?: model.identifier.address?.address
            ?: "Oops!",
            avatar = model.avatar,
        )
    }
}