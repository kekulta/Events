package com.kekulta.events.domain.models.base

import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.values.Avatar

data class CommunityModel(
    val id: CommunityId,
    val name: String,
    val description: String?,
    val avatar: Avatar,
)