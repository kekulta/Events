package com.kekulta.events.domain.formatters

import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.presentation.ui.models.ProfileVo

class ProfileFormatter() {
    fun format(model: ProfileModel): ProfileVo {
        return ProfileVo(
            name = "${model.info.name} ${model.info.surname ?: ""}",
            number = model.number.format(),
            avatar = model.info.avatar,
        )
    }
}