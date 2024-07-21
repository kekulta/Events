package com.kekulta.events.domain.models

data class CommunityDetailsModel(
    val community: CommunityModel,
    val events: List<EventModel>
)