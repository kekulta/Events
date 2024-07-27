package com.kekulta.events.domain.models.base

import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.values.Avatar
import com.kekulta.events.domain.models.values.Location
import kotlinx.datetime.LocalDateTime

data class EventModel(
    val id: EventId,
    val community: CommunityId?,
    val name: String,
    val description: String,
    val avatar: Avatar,
    val tags: List<String>,
    val date: LocalDateTime,
    val location: Location,
)