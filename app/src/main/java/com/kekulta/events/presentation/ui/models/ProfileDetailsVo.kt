package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.Avatar

data class ProfileDetailsVo(
    val name: String,
    val number: String,
    val avatar: Avatar,
)