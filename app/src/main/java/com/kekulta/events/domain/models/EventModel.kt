package com.kekulta.events.domain.models

import kotlinx.datetime.LocalDateTime

data class EventModel(
    val id: EventId,
    val name: String,
    val description: String,
    val avatar: Avatar,
    val mapUrl: String,
    val tags: List<String>,
    val date: LocalDateTime,
    // Should be some kind of geo tag probably
    val location: String,
    val attendees: List<UserId>
)