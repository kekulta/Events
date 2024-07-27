package com.kekulta.events.domain.stubs.service

import com.kekulta.events.domain.models.info.PersonalInfo
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.models.values.Avatar
import com.kekulta.events.domain.models.values.EmailAddress
import com.kekulta.events.domain.models.values.Identifier
import com.kekulta.events.domain.models.values.VerificationCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

internal class StubAuthService(
    private val stubUsersService: StubUsersService
) {
    private val authStatus: MutableStateFlow<AuthStatus> = MutableStateFlow(
        AuthStatus.Authorized(
            requireNotNull(
                stubUsersService.createProfile(
                    identifier = Identifier.Email(EmailAddress("admin@events.app")),
                    info = PersonalInfo(
                        avatar = Avatar(null),
                        name = "Admin",
                        surname = "Admin",
                    )
                )
            )
        )
    )

    fun observeAuthStatus(): StateFlow<AuthStatus> = authStatus.asStateFlow()

    fun sendCode(identifier: Identifier) {
        authStatus.compareAndSet(
            AuthStatus.Unauthorized, AuthStatus.NeedsVerification(identifier)
        )
    }

    fun checkCode(code: VerificationCode) {/*
            We accept only even codes.
         */
        if ((code.code.toIntOrNull() ?: 1) % 2 == 1) {
            return
        }

        val newStatus = authStatus.updateAndGet { status ->
            if (status is AuthStatus.NeedsVerification) {
                val profile = stubUsersService.getProfile(status.identifier)

                if (profile == null) {
                    AuthStatus.NeedsRegistration(status.identifier)
                } else {
                    AuthStatus.Authorized(profile.id)
                }
            } else {
                status
            }
        }
    }

    fun register(info: PersonalInfo) {
        val newStatus = authStatus.updateAndGet { status ->
            if (status is AuthStatus.NeedsRegistration) {
                val newUser = stubUsersService.createProfile(
                    info = info, identifier = status.identifier
                )

                newUser?.let { id -> AuthStatus.Authorized(id) } ?: status
            } else {
                status
            }
        }
    }

    fun logout() {
        val newStatus = authStatus.updateAndGet { AuthStatus.Unauthorized }
    }

    fun delete() {
        authStatus.update { status ->
            if (status is AuthStatus.Authorized) {
                stubUsersService.deleteUser(status.id)
                AuthStatus.Unauthorized
            } else {
                status
            }
        }
    }
}