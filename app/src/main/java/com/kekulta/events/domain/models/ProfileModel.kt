package com.kekulta.events.domain.models

data class ProfileModel(
    val id: UserId,
    val number: PhoneNumber,
    val info: PersonalInfo,
)

data class PersonalInfo(
    val avatar: Avatar,
    val name: String,
    val surname: String?,
)