package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.PhoneNumber

interface SendCodeInteractor {
    fun execute(number: PhoneNumber): Boolean
}
