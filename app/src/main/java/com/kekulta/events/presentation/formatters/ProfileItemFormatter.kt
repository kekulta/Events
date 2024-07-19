package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.presentation.ui.models.ProfileItemVo

class ProfileItemFormatter {
    fun format(model: ProfileModel): ProfileItemVo {
        return ProfileItemVo(
            name = "${model.info.name} ${model.info.surname ?: ""}",
            number = model.number.format(),
            avatar = model.info.avatar,
        )
    }
}