package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.id.CommunityId

data class CommunityDetailsVo(
    val id: CommunityId,
    val name: String,
    val description: String?,
    val events: List<EventItemVo>,
)