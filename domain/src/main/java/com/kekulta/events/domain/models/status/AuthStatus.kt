package com.kekulta.events.domain.models.status

import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.values.Identifier

sealed interface AuthStatus {
    data object Unauthorized : AuthStatus
    data class NeedsVerification(val identifier: Identifier) : AuthStatus
    data class NeedsRegistration(val identifier: Identifier) : AuthStatus
    data class Authorized(val id: UserId) : AuthStatus
}