package com.kekulta.events.domain.models.pagination

import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.status.EventStatus

sealed interface UsersQuery {

    data class User(
        val id: UserId,
    ) : UsersQuery

    data class Users(
        val ids: List<UserId>,
        val limit: Int,
        val offset: Int,
    ) : UsersQuery

    data class Event(
        val id: EventId,
        val limit: Int,
        val offset: Int,
    ) : UsersQuery

    data class Community(
        val id: CommunityId,
        val statusList: List<EventStatus>,
        val limit: Int,
        val offset: Int,
    ) : UsersQuery

    data class Search(
        val query: String,
        val statusList: List<EventStatus>,
        val limit: Int,
        val offset: Int,
    ) : UsersQuery

    data class Recommendation(
        val statusList: List<EventStatus>,
        val limit: Int,
        val offset: Int,
    ) : UsersQuery
}