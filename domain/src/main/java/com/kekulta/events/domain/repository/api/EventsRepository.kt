package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.info.EventInfo
import com.kekulta.events.domain.models.pagination.EventsQuery
import com.kekulta.events.domain.models.pagination.Page
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun observeEventsForQuery(query: EventsQuery): Flow<Page<EventModel>>

    suspend fun registerForEvent(id: EventId)
    suspend fun cancelRegistration(id: EventId)

    suspend fun createEvent(info: EventInfo, communityId: CommunityId?)
    suspend fun changeEvent(id: EventId, info: EventInfo)
    suspend fun deleteEvent(id: EventId)

    suspend fun kickFromEvent(id: EventId, userId: UserId)
}

