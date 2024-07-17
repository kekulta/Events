package com.kekulta.events.domain.models

data class ProfileModel(
    val id: UserId,
    val accessToken: AccessToken,
    val number: PhoneNumber,
    val avatar: Avatar,
    val name: String,
    val surname: String?,
)