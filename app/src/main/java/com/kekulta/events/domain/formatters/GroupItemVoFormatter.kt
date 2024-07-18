package com.kekulta.events.domain.formatters

import com.kekulta.events.presentation.ui.models.GroupItemVo
import com.kekulta.events.domain.models.GroupModel

class GroupItemVoFormatter() {
    fun format(models: List<GroupModel>): List<GroupItemVo> {
        return models.map { model ->
            GroupItemVo(
                id = model.id,
                name = model.name,
                avatar = model.avatar,
                membersCount = model.members.size,
            )
        }
    }
}