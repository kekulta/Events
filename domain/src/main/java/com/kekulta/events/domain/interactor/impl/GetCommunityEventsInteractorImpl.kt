package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetCommunityEventsInteractor
import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.pagination.EventsQuery
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.domain.models.status.EventStatus
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunityEventsInteractorImpl(
    private val eventsRepository: EventsRepository,
) : GetCommunityEventsInteractor {
    override fun execute(id: CommunityId, offset: Int, limit: Int): Flow<Page<EventModel>> {
        return eventsRepository.observeEventsForQuery(query(id, offset, limit))
    }

    private fun query(id: CommunityId, offset: Int, limit: Int) = EventsQuery.Community(
        id = id,
        statusList = listOf(EventStatus.ANY),
        limit = limit,
        offset = offset,
    )
}