package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.flow.StateFlow

class CurrentAuthStatusUseCase(
    private val authRepository: AuthRepository,
) {
    fun execute(): StateFlow<AuthStatus> {
        return authRepository.observeAuthStatus()
    }
}