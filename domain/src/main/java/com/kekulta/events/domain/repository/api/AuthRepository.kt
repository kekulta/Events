package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.info.PersonalInfo
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.models.values.Identifier
import com.kekulta.events.domain.models.values.VerificationCode
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    fun observeAuthStatus(): StateFlow<AuthStatus>

    suspend fun sendCode(identifier: Identifier)
    suspend fun checkCode(code: VerificationCode)
    suspend fun register(info: PersonalInfo)
    suspend fun logout()

    suspend fun deleteAccount()
}