package com.kekulta.events.domain.models.info

import com.kekulta.events.domain.models.values.Avatar

data class PersonalInfo(
    val avatar: Avatar,
    val name: String,
    val surname: String?,
)