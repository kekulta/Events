package com.kekulta.events.domain.models.values

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class AccessTokenModel(val token: String)