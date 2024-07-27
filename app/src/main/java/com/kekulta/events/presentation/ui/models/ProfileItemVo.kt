package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.values.Avatar

data class ProfileItemVo(
    val name: String,
    val number: String,
    val avatar: Avatar,
)