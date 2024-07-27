package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.values.Avatar
import com.kekulta.events.domain.models.id.EventId

data class EventItemVo(
    val id: EventId,
    val name: String,
    val date: String,
    val location: String,
    val tags: List<String>,
    val avatar: Avatar,
    val isPast: Boolean,
)