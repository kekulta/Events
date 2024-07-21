package com.kekulta.events.presentation.formatters

import com.kekulta.events.presentation.ui.models.CommunityItemVo
import com.kekulta.events.domain.models.CommunityModel

class CommunityItemFormatter() {
    fun format(models: List<CommunityModel>): List<CommunityItemVo> {
        return models.map { model ->
            CommunityItemVo(
                id = model.id,
                name = model.name,
                avatar = model.avatar,
                membersCount = model.members.size,
            )
        }
    }
}