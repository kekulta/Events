package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.UserId

data class VisitorVo(
    val id: UserId,
    val avatar: Avatar,
)