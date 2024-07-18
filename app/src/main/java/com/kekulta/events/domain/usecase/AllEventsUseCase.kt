package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.repository.api.EventStatus
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.presentation.ui.models.EventItemVo
import com.kekulta.events.domain.formatters.EventItemVoFormatter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class AllEventsUseCase(
    private val eventsRepository: EventsRepository,
    private val eventItemVoFormatter: EventItemVoFormatter,
) {
    fun execute(): Flow<List<EventItemVo>> {
        return eventsRepository.observeEventsForQuery(Query)
            .mapLatest { events -> eventItemVoFormatter.format(events) }
    }

    companion object {
        private val Query = EventsQuery.Recommendation(
            statusList = listOf(EventStatus.ACTIVE, EventStatus.PAST, EventStatus.FUTURE),
            limit = 25,
        )
    }
}