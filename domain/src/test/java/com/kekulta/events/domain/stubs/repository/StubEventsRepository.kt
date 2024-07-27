package com.kekulta.events.domain.stubs.repository

import com.kekulta.events.common.utils.isFuture
import com.kekulta.events.common.utils.isPast
import com.kekulta.events.common.utils.isToday
import com.kekulta.events.domain.stubs.service.StubAuthService
import com.kekulta.events.domain.stubs.service.StubEventsRegistrationService
import com.kekulta.events.domain.stubs.service.StubEventsService
import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.info.EventInfo
import com.kekulta.events.domain.models.pagination.EventsQuery
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.domain.models.pagination.emptyPage
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.models.status.EventStatus
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

internal class StubEventsRepository(
    private val authService: StubAuthService,
    private val eventsService: StubEventsService,
    private val eventsRegistrationService: StubEventsRegistrationService,
) : EventsRepository {
    private val events = eventsService.fetchEvents()

    override fun observeEventsForQuery(query: EventsQuery): Flow<Page<EventModel>> {
        return when (query) {
            is EventsQuery.Event -> {
                events.map { events -> Page(events.filter { event -> event.id == query.id }, 0, 1) }
            }

            is EventsQuery.Events -> {
                val ids = query.ids.toSet()

                events.map { events ->
                    Page(
                        events.filter { event -> event.id in ids }, 0, ids.size
                    )
                }
            }

            is EventsQuery.Recommendation -> {
                events.map { events ->
                    Page(
                        events.drop(query.offset).take(query.limit), query.offset, events.size
                    )
                }
            }

            is EventsQuery.Search -> throw NotImplementedError("Search is not implemented!")

            is EventsQuery.Community -> {
                events.map { events ->
                    val filteredEvents = events.filter { event ->
                        event.community == query.id && event.checkStatus(query.statusList)
                    }
                    Page(
                        filteredEvents.drop(query.offset).take(query.limit),
                        query.offset,
                        filteredEvents.size,
                    )
                }
            }

            is EventsQuery.User -> {
                combine(
                    eventsRegistrationService.fetchRegistrations().map { registrations ->
                        registrations.mapNotNull { (userId, eventId) -> eventId.takeIf { userId == query.id } }
                    }, eventsService.fetchEvents()
                ) { ids, events ->
                    val idsSet = ids.toSet()
                    val filteredEvents =
                        events.filter { event -> event.id in idsSet && event.checkStatus(query.statusList) }
                    Page(
                        filteredEvents.drop(query.offset).take(query.limit),
                        query.offset,
                        filteredEvents.size
                    )
                }
            }

            is EventsQuery.Subscription -> {
                combine(
                    eventsRegistrationService.fetchRegistrations().map { registrations ->
                        registrations.mapNotNull { (userId, eventId) -> eventId.takeIf { userId == query.userId && eventId == query.eventId } }
                    }, eventsService.fetchEvents()
                ) { ids, events ->

                    if (ids.isEmpty()) {
                        emptyPage<EventModel>()
                    } else {
                        Page(
                            events.filter { event -> event.id == ids.first() },
                            0,
                            1,
                        )
                    }
                }
            }
        }
    }

    override suspend fun registerForEvent(id: EventId) {
        (authService.observeAuthStatus().value as? AuthStatus.Authorized)?.id?.let { userId ->
            eventsRegistrationService.register(userId, id)
        }
    }

    override suspend fun cancelRegistration(id: EventId) {
        (authService.observeAuthStatus().value as? AuthStatus.Authorized)?.id?.let { userId ->
            eventsRegistrationService.cancel(userId, id)
        }
    }

    override suspend fun createEvent(
        info: EventInfo, communityId: CommunityId?
    ) {
        eventsService.createEvent(communityId, info)
    }

    override suspend fun changeEvent(id: EventId, info: EventInfo) {
        eventsService.changeEvent(id, info)
    }

    override suspend fun deleteEvent(id: EventId) {
        eventsService.deleteEvent(id)
    }

    override suspend fun kickFromEvent(id: EventId, userId: UserId) {
        eventsRegistrationService.cancel(userId, id)
    }

    private fun EventModel.checkStatus(statusList: List<EventStatus>): Boolean {
        return statusList.contains(EventStatus.ANY) || (statusList.contains(EventStatus.ACTIVE) && isActive()) || (statusList.contains(
            EventStatus.PAST
        ) && isPast()) || (statusList.contains(EventStatus.FUTURE) && isFuture())
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
