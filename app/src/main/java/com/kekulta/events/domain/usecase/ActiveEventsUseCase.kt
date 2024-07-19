package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.formatters.ActiveEventItemVoFormatter
import com.kekulta.events.domain.repository.api.EventStatus
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.presentation.ui.models.ActiveEventItemVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class ActiveEventsUseCase(
    private val eventsRepository: EventsRepository,
    private val activeEventItemVoFormatter: ActiveEventItemVoFormatter
) {
    /*
        I do not use `operator fun invoke()` on purpose. I prefer explicit function over implicit
        one. Operator overloading may cause confusion and should used very carefully.
        I don't think that usecases are tha place to use them.
     */
    fun execute(): Flow<List<ActiveEventItemVo>> {
        return eventsRepository.observeEventsForQuery(Query)
            .mapLatest { events -> activeEventItemVoFormatter.format(events) }
    }

    companion object {
        private val Query = EventsQuery.Recommendation(
            statusList = listOf(EventStatus.ACTIVE),
            limit = 25,
        )
    }
}