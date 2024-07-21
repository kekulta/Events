package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.flow.StateFlow

class CurrentAuthStatusInteractor(
    private val authRepository: AuthRepository,
) {
    fun execute(): StateFlow<AuthStatus> {
        return authRepository.observeAuthStatus()
    }
}