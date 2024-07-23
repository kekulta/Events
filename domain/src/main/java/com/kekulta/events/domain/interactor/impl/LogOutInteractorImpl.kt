package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.domain.repository.api.ProfileRepository

class LogOutInteractorImpl(
    private val profileRepository: ProfileRepository,
) : LogOutInteractor {
    override fun execute() {
        profileRepository.logOut()
    }
}