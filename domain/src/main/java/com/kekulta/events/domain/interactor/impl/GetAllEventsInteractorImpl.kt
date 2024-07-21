package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetAllEventsInteractor
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.repository.api.EventStatus
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.flow.Flow

class GetAllEventsInteractorImpl(
    private val eventsRepository: EventsRepository,
) : GetAllEventsInteractor {
    override fun execute(): Flow<List<EventModel>> {
        return eventsRepository.observeEventsForQuery(Query)
    }

    companion object {
        private val Query = EventsQuery.Recommendation(
            statusList = listOf(EventStatus.ACTIVE, EventStatus.PAST, EventStatus.FUTURE),
            limit = 25,
        )
    }
}