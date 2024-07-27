package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.RegisterInteractor
import com.kekulta.events.domain.models.info.PersonalInfo
import com.kekulta.events.domain.repository.api.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RegisterInteractorImpl(
    private val authRepository: AuthRepository,
) : RegisterInteractor {
    override suspend fun execute(info: PersonalInfo) = withContext(Dispatchers.IO) {
        authRepository.register(info)
    }
}