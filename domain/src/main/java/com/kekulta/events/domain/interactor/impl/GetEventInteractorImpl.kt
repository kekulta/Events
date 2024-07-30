package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetEventInteractor
import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.pagination.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class GetEventInteractorImpl(
    private val eventsRepository: EventsRepository,
) : GetEventInteractor {
    override fun execute(id: EventId): Flow<EventModel?> {
        return eventsRepository.observeEventsForQuery(EventsQuery.Event(id)).map { page ->
            page.values.firstOrNull()
        }
    }
}