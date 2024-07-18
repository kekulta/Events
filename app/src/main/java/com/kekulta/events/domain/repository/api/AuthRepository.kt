package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.models.VerificationCode
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    fun observeAuthStatus(): StateFlow<AuthStatus>
    fun sendCode(number: PhoneNumber): Boolean
    fun checkCode(code: VerificationCode): Boolean
    fun register(info: PersonalInfo): Boolean
}