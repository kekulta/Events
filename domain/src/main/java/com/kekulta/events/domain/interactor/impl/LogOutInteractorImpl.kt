package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class LogOutInteractorImpl(
    private val authRepository: AuthRepository,
) : LogOutInteractor {
    override suspend fun execute() = withContext(Dispatchers.IO) {
        authRepository.logout()
    }
}