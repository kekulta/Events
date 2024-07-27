package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.base.CommunityModel
import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.presentation.ui.models.CommunityDetailsVo

class CommunityDetailsFormatter(
    private val eventItemFormatter: EventItemFormatter,
) {
    fun format(community: CommunityModel, events: List<EventModel>): CommunityDetailsVo {
        return CommunityDetailsVo(
            id = community.id,
            name = community.name,
            description = community.description,
            events = events.map { event -> eventItemFormatter.format(event) },
        )
    }
}