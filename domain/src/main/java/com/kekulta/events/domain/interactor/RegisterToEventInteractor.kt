package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.EventId

interface RegisterToEventInteractor {
    suspend fun execute(id: EventId): Boolean
}
