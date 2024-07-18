package com.kekulta.events.domain.repository.mock

import com.kekulta.events.data.MockAuthService
import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.models.VerificationCode
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.flow.StateFlow

class AuthRepositoryMock(
    private val mockAuthService: MockAuthService,
) : AuthRepository {
    override fun observeAuthStatus(): StateFlow<AuthStatus> = mockAuthService.observeAuthStatus()

    override fun sendCode(number: PhoneNumber): Boolean = mockAuthService.sendCode(number)

    override fun checkCode(code: VerificationCode): Boolean = mockAuthService.checkCode(code)

    override fun register(info: PersonalInfo): Boolean = mockAuthService.register(info)
}