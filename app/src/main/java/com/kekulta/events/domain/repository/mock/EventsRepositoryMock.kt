package com.kekulta.events.domain.repository.mock

import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.mock.MockFunctions.mockEventModels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class EventsRepositoryMock : EventsRepository {
    private val eventsMap = mockEventModels(25).associateBy { event -> event.id }.toMutableMap()
    private val events = MutableStateFlow(eventsMap.values.toSet())

    override fun observeEventDetails(id: EventId): Flow<EventModel?> {
        return events.map { events -> events.firstOrNull { event -> event.id == id } }
    }

    override suspend fun registerForEvent(id: EventId, userId: UserId): Boolean {
        val event = eventsMap[id] ?: return false
        val isRegistered = event.attendees.contains(userId)
        if (isRegistered) {
            return false
        }
        eventsMap[id] = event.copy(attendees = event.attendees + userId)
        events.update { eventsMap.values.toSet() }

        return true
    }

    override suspend fun cancelRegistration(id: EventId, userId: UserId): Boolean {
        val event = eventsMap[id] ?: return false
        val attendees = event.attendees.filterNot { user -> user.id == userId.id }
        if (attendees.size == event.attendees.size) {
            return false
        }
        eventsMap[id] = event.copy(attendees = attendees)
        events.update { eventsMap.values.toSet() }

        return true
    }
}