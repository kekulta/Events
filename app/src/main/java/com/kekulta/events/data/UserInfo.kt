package com.kekulta.events.data

import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.models.PhoneNumber

data class UserInfo(
    val number: PhoneNumber,
    val info: PersonalInfo,
)