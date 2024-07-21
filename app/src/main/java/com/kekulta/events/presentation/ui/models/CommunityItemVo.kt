package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.CommunityId

data class CommunityItemVo(
    val id: CommunityId,
    val name: String,
    val avatar: Avatar,
    val membersCount: Int,
)