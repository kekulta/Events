package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.repository.api.EventStatus
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.flow.Flow

class AllEventsUseCase(
    private val eventsRepository: EventsRepository,
) {
    fun execute(): Flow<List<EventModel>> {
        return eventsRepository.observeEventsForQuery(Query)
    }

    companion object {
        private val Query = EventsQuery.Recommendation(
            statusList = listOf(EventStatus.ACTIVE, EventStatus.PAST, EventStatus.FUTURE),
            limit = 25,
        )
    }
}