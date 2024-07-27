package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.values.Avatar
import com.kekulta.events.domain.models.id.UserId

data class VisitorVo(
    val id: UserId,
    val avatar: Avatar,
)