package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.EventDetailsModel
import com.kekulta.events.domain.models.EventId
import kotlinx.coroutines.flow.Flow

interface GetEventDetailsInteractor {
    fun execute(id: EventId): Flow<EventDetailsModel?>
}
