package com.kekulta.events.domain.stubs.repository

import com.kekulta.events.domain.stubs.service.StubAuthService
import com.kekulta.events.domain.models.info.PersonalInfo
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.models.values.Identifier
import com.kekulta.events.domain.models.values.VerificationCode
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.flow.StateFlow

internal class StubAuthRepository(
    private val stubAuthService: StubAuthService,
) : AuthRepository {
    override fun observeAuthStatus(): StateFlow<AuthStatus> = stubAuthService.observeAuthStatus()

    override suspend fun sendCode(identifier: Identifier) = stubAuthService.sendCode(identifier)
    override suspend fun checkCode(code: VerificationCode) = stubAuthService.checkCode(code)
    override suspend fun register(info: PersonalInfo) = stubAuthService.register(info)
    override suspend fun logout() = stubAuthService.logout()
    override suspend fun deleteAccount()= stubAuthService.delete()
}