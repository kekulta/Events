package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.UserId
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun observeEventsForQuery(query: EventsQuery): Flow<List<EventModel>>
    fun observeEventDetails(id: EventId): Flow<EventModel?>
    suspend fun registerForEvent(id: EventId, userId: UserId): Boolean
    suspend fun cancelRegistration(id: EventId, userId: UserId): Boolean
}

sealed interface EventsQuery {
    val types: List<EventType>
    val limit: Int

    data class Search(
        val query: String, override val types: List<EventType>, override val limit: Int
    ) : EventsQuery

    data class Recommendation(override val types: List<EventType>, override val limit: Int) :
        EventsQuery
}

enum class EventType {
    PAST, ACTIVE, FUTURE,
}