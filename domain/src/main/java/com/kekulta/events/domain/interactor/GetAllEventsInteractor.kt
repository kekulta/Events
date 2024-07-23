package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.EventModel
import kotlinx.coroutines.flow.Flow

interface GetAllEventsInteractor {
    fun execute(): Flow<List<EventModel>>
}
