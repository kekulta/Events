package com.kekulta.events.domain.repository.mock

import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.repository.api.EventType
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.mock.functions.mockEventModels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class EventsRepositoryMock : EventsRepository {
    private val eventsMap = mockEventModels(25).associateBy { event -> event.id }.toMutableMap()
    private val eventsFlow = MutableStateFlow(eventsMap.values.toSet())

    override fun observeEventsForQuery(query: EventsQuery): Flow<List<EventModel>> {
        return when (query) {
            is EventsQuery.Recommendation -> {
                eventsFlow.map { events ->
                    events.filter { event ->
                        (query.types.contains(EventType.ACTIVE) && isActive(event)) || (query.types.contains(
                            EventType.PAST
                        ) && isPast(event)) || (query.types.contains(EventType.FUTURE) && isFuture(
                            event
                        ))
                    }.take(query.limit)
                }
            }

            is EventsQuery.Search -> throw NotImplementedError("Search is not implemented!")
        }
    }

    override fun observeEventDetails(id: EventId): Flow<EventModel?> {
        return eventsFlow.map { events -> events.firstOrNull { event -> event.id == id } }
    }

    override suspend fun registerForEvent(id: EventId, userId: UserId): Boolean {
        val event = eventsMap[id] ?: return false
        val isRegistered = event.attendees.contains(userId)
        if (isRegistered) {
            return false
        }
        eventsMap[id] = event.copy(attendees = event.attendees + userId)
        eventsFlow.update { eventsMap.values.toSet() }

        return true
    }

    override suspend fun cancelRegistration(id: EventId, userId: UserId): Boolean {
        val event = eventsMap[id] ?: return false
        val attendees = event.attendees.filterNot { user -> user.id == userId.id }
        if (attendees.size == event.attendees.size) {
            return false
        }
        eventsMap[id] = event.copy(attendees = attendees)
        eventsFlow.update { eventsMap.values.toSet() }

        return true
    }

    private fun isActive(event: EventModel): Boolean {
        return event.date.date.isToday()
    }


    private fun isPast(event: EventModel): Boolean {
        return event.date.date.isPast()
    }


    private fun isFuture(event: EventModel): Boolean {
        return event.date.date.isFuture()
    }
}


fun LocalDate.isToday(timeZone: TimeZone = TimeZone.currentSystemDefault()): Boolean {
    return this == Clock.System.now().toLocalDateTime(timeZone).date
}

fun LocalDate.isPast(timeZone: TimeZone = TimeZone.currentSystemDefault()): Boolean {
    return this < Clock.System.now().toLocalDateTime(timeZone).date
}

fun LocalDate.isFuture(timeZone: TimeZone = TimeZone.currentSystemDefault()): Boolean {
    return this > Clock.System.now().toLocalDateTime(timeZone).date
}
