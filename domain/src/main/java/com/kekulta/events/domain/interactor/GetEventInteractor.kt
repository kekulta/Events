package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.id.EventId
import kotlinx.coroutines.flow.Flow

interface GetEventInteractor {
    fun execute(id: EventId): Flow<EventModel?>
}
