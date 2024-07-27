package com.kekulta.events.domain.models.pagination

import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.status.EventStatus

sealed interface EventsQuery {
    data class Event(
        val id: EventId,
    ) : EventsQuery

    data class Events(
        val ids: List<EventId>,
    ) : EventsQuery

    data class Search(
        val query: String,
        val statusList: List<EventStatus>,
        val limit: Int,
        val offset: Int,
    ) : EventsQuery

    data class Recommendation(
        val statusList: List<EventStatus>,
        val limit: Int,
        val offset: Int,
    ) : EventsQuery

    data class User(
        val id: UserId,
        val statusList: List<EventStatus>,
        val limit: Int,
        val offset: Int,
    ) : EventsQuery

    data class Subscription(
        val userId: UserId,
        val eventId: EventId,
    ) : EventsQuery

    data class Community(
        val id: CommunityId,
        val statusList: List<EventStatus>,
        val limit: Int,
        val offset: Int,
    ) : EventsQuery
}