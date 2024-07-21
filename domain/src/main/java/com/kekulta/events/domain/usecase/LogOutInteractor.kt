package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.repository.api.ProfileRepository

class LogOutInteractor(
    private val profileRepository: ProfileRepository,
) {
    fun execute() = profileRepository.logOut()
}