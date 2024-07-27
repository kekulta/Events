package com.kekulta.events.data.mock.repository

import com.kekulta.events.data.mock.service.MockAuthService
import com.kekulta.events.domain.models.info.PersonalInfo
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.models.values.Identifier
import com.kekulta.events.domain.models.values.VerificationCode
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.flow.StateFlow

internal class AuthRepositoryMock(
    private val mockAuthService: MockAuthService,
) : AuthRepository {
    override fun observeAuthStatus(): StateFlow<AuthStatus> = mockAuthService.observeAuthStatus()

    override suspend fun sendCode(identifier: Identifier) = mockAuthService.sendCode(identifier)
    override suspend fun checkCode(code: VerificationCode) = mockAuthService.checkCode(code)
    override suspend fun register(info: PersonalInfo) = mockAuthService.register(info)
    override suspend fun logout() = mockAuthService.logout()
    override suspend fun deleteAccount()= mockAuthService.delete()
}