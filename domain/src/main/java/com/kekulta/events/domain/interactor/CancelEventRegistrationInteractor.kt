package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.id.EventId

interface CancelEventRegistrationInteractor {
    suspend fun execute(id: EventId)
}
