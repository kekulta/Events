package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetActiveEventsInteractor
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.repository.api.EventStatus
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.flow.Flow

internal class GetActiveEventsInteractorImpl(
    private val eventsRepository: EventsRepository,
) : GetActiveEventsInteractor {

    override fun execute(): Flow<List<EventModel>> {
        return eventsRepository.observeEventsForQuery(Query)
    }

    companion object {
        private val Query = EventsQuery.Recommendation(
            statusList = listOf(EventStatus.ACTIVE),
            limit = 25,
        )
    }
}