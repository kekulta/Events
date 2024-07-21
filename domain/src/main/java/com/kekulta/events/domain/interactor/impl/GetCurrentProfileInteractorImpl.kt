package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetCurrentProfileInteractor
import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.repository.api.ProfileRepository
import kotlinx.coroutines.flow.StateFlow

class GetCurrentProfileInteractorImpl(
    private val profileRepository: ProfileRepository,
): GetCurrentProfileInteractor {
    override fun execute(): StateFlow<ProfileModel?> {
        return profileRepository.observeCurrentProfile()
    }
}