package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.UserId
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun observeEventDetails(id: EventId): Flow<EventModel?>
    suspend fun registerForEvent(id: EventId, userId: UserId): Boolean
    suspend fun cancelRegistration(id: EventId, userId: UserId): Boolean
}