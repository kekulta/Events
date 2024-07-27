package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.SendCodeInteractor
import com.kekulta.events.domain.models.values.Identifier
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SendCodeInteractorImpl(
    private val authRepository: AuthRepository,
) : SendCodeInteractor {
    override suspend fun execute(identifier: Identifier) = withContext(Dispatchers.IO) {
        authRepository.sendCode(identifier)
    }
}