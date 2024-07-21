package com.kekulta.events.domain.models

data class CommunityModel(
    val id: CommunityId,
    val name: String,
    val description: String?,
    val avatar: Avatar,
    val members: List<UserId>,
    val events: List<EventId>
)