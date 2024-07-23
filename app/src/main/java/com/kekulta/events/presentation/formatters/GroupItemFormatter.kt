package com.kekulta.events.presentation.formatters

import com.kekulta.events.presentation.ui.models.GroupItemVo
import com.kekulta.events.domain.models.GroupModel

class GroupItemFormatter() {
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