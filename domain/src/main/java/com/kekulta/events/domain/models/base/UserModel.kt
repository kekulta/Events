package com.kekulta.events.domain.models.base

import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.values.Avatar
import com.kekulta.events.domain.models.values.EmailAddress
import com.kekulta.events.domain.models.values.PhoneNumber

data class UserModel(
    val id: UserId,
    val name: String,
    val surname: String?,
    val avatar: Avatar,
    val number: PhoneNumber?,
    val email: EmailAddress?,
)