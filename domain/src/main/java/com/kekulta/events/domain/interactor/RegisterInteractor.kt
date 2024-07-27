package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.info.PersonalInfo

interface RegisterInteractor {
    suspend fun execute(info: PersonalInfo)
}
