package com.kekulta.events.domain.repository.mock

import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.repository.api.EventStatus
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.mock.functions.mockEventModels
import com.kekulta.events.utils.isFuture
import com.kekulta.events.utils.isPast
import com.kekulta.events.utils.isToday
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class EventsRepositoryMock : EventsRepository {
    private val eventsMap = mockEventModels(25).associateBy { event -> event.id }.toMutableMap()

    // Set would probably work better but lets not overengineer
    private val eventsFlow = MutableStateFlow(eventsMap.values.toList())

    override fun observeEventsForQuery(query: EventsQuery): Flow<List<EventModel>> {
        return when (query) {
            is EventsQuery.Recommendation -> {
                eventsFlow.mapLatest { events ->
                    events.filterEvents(query)
                }
            }

            is EventsQuery.Search -> throw NotImplementedError("Search is not implemented!")

            is EventsQuery.User -> {
                eventsFlow.mapLatest { events ->
                    events.filterEvents(query) { event ->
                        event.attendees.contains(
                            query.id
                        )
                    }
                }
            }
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
        eventsFlow.update { eventsMap.values.toList() }

        return true
    }

    override suspend fun cancelRegistration(id: EventId, userId: UserId): Boolean {
        val event = eventsMap[id] ?: return false
        val attendees = event.attendees.filterNot { user -> user.id == userId.id }
        if (attendees.size == event.attendees.size) {
            return false
        }
        eventsMap[id] = event.copy(attendees = attendees)
        eventsFlow.update { eventsMap.values.toList() }

        return true
    }

    private fun List<EventModel>.filterEvents(
        defaultConstraints: EventsQuery,
        additionalConstraints: ((event: EventModel) -> Boolean)? = null,
    ): List<EventModel> {
        return this.filter { event ->
            (event.checkStatus(defaultConstraints.statusList))
                    && (additionalConstraints?.invoke(event) ?: true)
        }.take(defaultConstraints.limit)
    }

    private fun EventModel.checkStatus(statusList: List<EventStatus>): Boolean {
        return (statusList.contains(EventStatus.ACTIVE) && isActive())
                || (statusList.contains(EventStatus.PAST) && isPast())
                || (statusList.contains(EventStatus.FUTURE) && isFuture())
    }

    private fun EventModel.isActive(): Boolean {
        return date.date.isToday()
    }


    private fun EventModel.isPast(): Boolean {
        return date.date.isPast()
    }


    private fun EventModel.isFuture(): Boolean {
        return date.date.isFuture()
    }
}
