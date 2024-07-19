package com.kekulta.events.domain.models

data class UserModel(
    val id: UserId,
    val name: String,
    val surname: String?,
    val avatar: Avatar,
)