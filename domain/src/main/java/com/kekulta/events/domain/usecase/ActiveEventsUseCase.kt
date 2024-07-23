package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.repository.api.EventStatus
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.flow.Flow

class ActiveEventsUseCase(
    private val eventsRepository: EventsRepository,
) {
    /*
        I do not use `operator fun invoke()` on purpose. I prefer explicit function over implicit
        one. Operator overloading may cause confusion and should used very carefully.
        I don't think that usecases are the place to use them.
     */
    fun execute(): Flow<List<EventModel>> {
        return eventsRepository.observeEventsForQuery(Query)
    }

    companion object {
        private val Query = EventsQuery.Recommendation(
            statusList = listOf(EventStatus.ACTIVE),
            limit = 25,
        )
    }
}