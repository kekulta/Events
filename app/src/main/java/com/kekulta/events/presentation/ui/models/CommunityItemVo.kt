package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.values.Avatar
import com.kekulta.events.domain.models.id.CommunityId

data class CommunityItemVo(
    val id: CommunityId,
    val name: String,
    val avatar: Avatar,
    val membersCount: Int,
)