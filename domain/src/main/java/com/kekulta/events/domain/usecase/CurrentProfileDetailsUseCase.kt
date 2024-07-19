package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.repository.api.ProfileRepository
import kotlinx.coroutines.flow.StateFlow

class CurrentProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    fun execute(): StateFlow<ProfileModel?> {
        return profileRepository.observeCurrentProfile()
    }
}