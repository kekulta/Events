package com.kekulta.events.domain.models.info

import com.kekulta.events.domain.models.values.Avatar

data class CommunityInfo(
    val name: String,
    val description: String?,
    val avatar: Avatar,
)