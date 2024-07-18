package com.kekulta.events.data

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.models.VerificationCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

class MockAuthService(
    private val mockUsersService: MockUsersService
) {
    private val authStatus: MutableStateFlow<AuthStatus> =
        MutableStateFlow(
            AuthStatus.Authorized(
                mockUsersService.createUser(
                    UserInfo(
                        number = PhoneNumber(
                            code = "+7",
                            number = "9959177242"
                        ),
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

    fun sendCode(number: PhoneNumber): Boolean {
        return authStatus.compareAndSet(AuthStatus.Unauthorized, AuthStatus.CodeSent(number))
    }

    fun checkCode(code: VerificationCode): Boolean {
        val newStatus = authStatus.updateAndGet { status ->
            if (status is AuthStatus.CodeSent) {
                val profile = mockUsersService.getProfile(status.number)

                if (profile == null) {
                    AuthStatus.NeedsRegistration(status.number)
                } else {
                    AuthStatus.Authorized(profile)
                }
            } else {
                status
            }
        }

        return newStatus is AuthStatus.NeedsRegistration || newStatus is AuthStatus.Authorized
    }

    fun register(info: PersonalInfo): Boolean {
        val newStatus = authStatus.updateAndGet { status ->
            if (status is AuthStatus.NeedsRegistration) {
                val newUser = mockUsersService.createUser(
                    UserInfo(
                        number = status.number,
                        info = info,
                    )
                )

                AuthStatus.Authorized(newUser)
            } else {
                status
            }
        }

        return newStatus is AuthStatus.Authorized
    }

    fun logOut(): Boolean {
        val newStatus = authStatus.updateAndGet { AuthStatus.Unauthorized }

        return newStatus == AuthStatus.Unauthorized
    }
}