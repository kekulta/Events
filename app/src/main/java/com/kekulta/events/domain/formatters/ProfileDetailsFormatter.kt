package com.kekulta.events.domain.formatters

import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.presentation.ui.models.ProfileDetailsVo

class ProfileDetailsFormatter() {
    fun format(model: ProfileModel): ProfileDetailsVo {
        return ProfileDetailsVo(
            name = "${model.info.name} ${model.info.surname ?: ""}",
            number = model.number.format(),
            avatar = model.info.avatar,
        )
    }
}