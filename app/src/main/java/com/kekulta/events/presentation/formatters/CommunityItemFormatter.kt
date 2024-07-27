package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.base.CommunityModel
import com.kekulta.events.presentation.ui.models.CommunityItemVo

class CommunityItemFormatter() {
    fun format(models: List<CommunityModel>, membersCount: List<Int>): List<CommunityItemVo> {
        return models.mapIndexed { index, model ->
            CommunityItemVo(
                id = model.id,
                name = model.name,
                avatar = model.avatar,
                membersCount = membersCount.getOrElse(index) { 0 },
            )
        }
    }
}