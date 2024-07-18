package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.EventId

data class ActiveEventItemVo(
    val id: EventId,
    val name: String,
    val date: String,
    val location: String,
    val tags: List<String>,
    val avatar: Avatar,
)