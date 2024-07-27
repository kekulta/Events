package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetCurrentAuthStatusInteractor
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.flow.StateFlow

internal class GetCurrentAuthStatusInteractorImpl(
    private val authRepository: AuthRepository,
): GetCurrentAuthStatusInteractor {
    override fun execute(): StateFlow<AuthStatus> {
        return authRepository.observeAuthStatus()
    }
}