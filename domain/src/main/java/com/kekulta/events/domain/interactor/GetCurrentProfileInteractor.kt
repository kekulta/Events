package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.base.ProfileModel
import kotlinx.coroutines.flow.StateFlow

interface GetCurrentProfileInteractor {
    fun execute(): StateFlow<ProfileModel?>
}
