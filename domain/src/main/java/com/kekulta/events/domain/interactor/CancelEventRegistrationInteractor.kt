package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.EventId

interface CancelEventRegistrationInteractor {
    suspend fun execute(id: EventId): Boolean
}
