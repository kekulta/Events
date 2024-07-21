package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.repository.api.ProfileRepository
import kotlinx.coroutines.flow.StateFlow

class CurrentProfileInteractor(
    private val profileRepository: ProfileRepository,
) {
    fun execute(): StateFlow<ProfileModel?> {
        return profileRepository.observeCurrentProfile()
    }
}