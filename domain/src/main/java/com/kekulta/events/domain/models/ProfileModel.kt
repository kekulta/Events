package com.kekulta.events.domain.models

data class ProfileModel(
    val id: UserId,
    val number: PhoneNumber,
    val info: PersonalInfo,
)

