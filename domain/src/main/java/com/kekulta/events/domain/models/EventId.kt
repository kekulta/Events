package com.kekulta.events.domain.models

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class EventId(val id: String)