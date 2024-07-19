package com.kekulta.events.domain.models

sealed interface AuthStatus {
    data object Unauthorized : AuthStatus
    data class CodeSent(val number: PhoneNumber) : AuthStatus
    data class NeedsRegistration(val number: PhoneNumber) : AuthStatus
    data class Authorized(val profile: ProfileModel) : AuthStatus
}