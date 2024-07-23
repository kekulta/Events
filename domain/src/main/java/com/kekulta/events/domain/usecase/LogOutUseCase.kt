package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.repository.api.ProfileRepository

class LogOutUseCase(
    private val profileRepository: ProfileRepository,
) {
    fun execute() = profileRepository.logOut()
}