package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.GroupId

data class GroupItemVo(
    val id: GroupId,
    val name: String,
    val avatar: Avatar,
    val membersCount: Int,
)