package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.GroupId

data class GroupDetailsVo(
    val id: GroupId,
    val name: String,
    val description: String?,
    val events: List<EventItemVo>,
)