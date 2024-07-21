package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.CommunityDetailsModel
import com.kekulta.events.presentation.ui.models.CommunityDetailsVo

class CommunityDetailsFormatter(
    private val eventItemFormatter: EventItemFormatter,
) {
    fun format(model: CommunityDetailsModel): CommunityDetailsVo {
        return CommunityDetailsVo(
            id = model.community.id,
            name = model.community.name,
            description = model.community.description,
            events = eventItemFormatter.format(model.events),
        )
    }
}