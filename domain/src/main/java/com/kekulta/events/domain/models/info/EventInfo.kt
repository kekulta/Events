package com.kekulta.events.domain.models.info

import com.kekulta.events.domain.models.values.Avatar
import com.kekulta.events.domain.models.values.Location
import kotlinx.datetime.LocalDateTime

data class EventInfo(
    val name: String,
    val description: String,
    val avatar: Avatar,
    val tags: List<String>,
    val date: LocalDateTime,
    val location: Location,
)