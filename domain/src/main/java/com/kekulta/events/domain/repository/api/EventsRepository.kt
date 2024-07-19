package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.UserId
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun observeEventsForQuery(query: EventsQuery): Flow<List<EventModel>>
    fun observeEvent(id: EventId): Flow<EventModel?>
    fun observeEvents(ids: List<EventId>): Flow<List<EventModel>>
    suspend fun registerForEvent(id: EventId, userId: UserId): Boolean
    suspend fun cancelRegistration(id: EventId, userId: UserId): Boolean
}

sealed interface EventsQuery {
    val statusList: List<EventStatus>
    val limit: Int

    data class Search(
        val query: String, override val statusList: List<EventStatus>, override val limit: Int,
    ) : EventsQuery

    data class Recommendation(
        override val statusList: List<EventStatus>, override val limit: Int,
    ) : EventsQuery


    data class User(
        val id: UserId, override val statusList: List<EventStatus>, override val limit: Int,
    ) : EventsQuery
}

enum class EventStatus {
    PAST, ACTIVE, FUTURE,
}