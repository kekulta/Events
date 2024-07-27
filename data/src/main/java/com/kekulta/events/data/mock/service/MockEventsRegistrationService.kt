package com.kekulta.events.data.mock.service

import com.kekulta.events.data.mock.functions.mockRegistrations
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.id.UserId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class MockEventsRegistrationService {

    private val registrations = MutableStateFlow(mockRegistrations(50, 50))

    fun fetchRegistrations(): Flow<List<Pair<UserId, EventId>>> = registrations

    fun register(userId: UserId, eventId: EventId) {
        registrations.update { registrations ->
            registrations.filterNot { it == userId to eventId } + (userId to eventId)
        }
    }

    fun cancel(userId: UserId, eventId: EventId) {
        registrations.update { registrations ->
            registrations.filterNot { it == userId to eventId }
        }
    }


    fun cancel(userId: UserId) {
        registrations.update { registrations ->
            registrations.filterNot { it.first == userId }
        }
    }

    fun cancel(eventId: EventId) {
        registrations.update { registrations ->
            registrations.filterNot { it.second == eventId }
        }
    }
}