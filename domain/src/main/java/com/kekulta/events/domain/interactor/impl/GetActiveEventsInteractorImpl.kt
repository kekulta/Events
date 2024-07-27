package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetActiveEventsInteractor
import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.pagination.EventsQuery
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.domain.models.status.EventStatus
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.flow.Flow

internal class GetActiveEventsInteractorImpl(
    private val eventsRepository: EventsRepository,
) : GetActiveEventsInteractor {

    override fun execute(offset: Int, limit: Int): Flow<Page<EventModel>> {
        return eventsRepository.observeEventsForQuery(query(offset, limit))
    }

    private fun query(offset: Int, limit: Int) = EventsQuery.Recommendation(
        statusList = listOf(EventStatus.ACTIVE),
        limit = limit,
        offset = offset,
    )
}