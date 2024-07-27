package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.id.EventId

interface RegisterToEventInteractor {
    suspend fun execute(id: EventId)
}
