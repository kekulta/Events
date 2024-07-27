package com.kekulta.events.domain.models.base

import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.values.Avatar
import com.kekulta.events.domain.models.values.Identifier

/**
 * @property id
 * Unique immutable id issued by the server after registration
 * @property identifier
 * Unique yet mutable identifiers chose by the user to register. It could be email or phone number.
 * User must have at least one identifier and it must be validated by the server.
 */
data class ProfileModel(
    val id: UserId,
    val identifier: Identifier,
    val name: String,
    val surname: String?,
    val avatar: Avatar,
)
