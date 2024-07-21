package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.PersonalInfo

interface RegisterInteractor {
    fun execute(info: PersonalInfo): Boolean
}
