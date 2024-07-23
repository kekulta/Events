package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.GroupDetailsModel
import com.kekulta.events.presentation.ui.models.GroupDetailsVo

class GroupDetailsFormatter(
    private val eventItemFormatter: EventItemFormatter,
) {
    fun format(model: GroupDetailsModel): GroupDetailsVo {
        return GroupDetailsVo(
            id = model.group.id,
            name = model.group.name,
            description = model.group.description ?: "No description.",
            events = eventItemFormatter.format(model.events),
        )
    }
}