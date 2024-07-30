package com.kekulta.events.domain.stubs.service

import com.kekulta.events.domain.stubs.functions.generateStubEvents
import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.info.EventInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

internal class StubEventsService {
    private var events = MutableStateFlow(generateStubEvents(50))

    fun fetchEvents(): Flow<List<EventModel>> {
        return events
    }

    fun createEvent(communityId: CommunityId?, info: EventInfo): EventId {
        val newEvent = EventModel(
            id = EventId(Random.nextInt().toString()),
            community = communityId,
            name = info.name,
            description = info.description,
            avatar = info.avatar,
            tags = info.tags,
            date = info.date,
            location = info.location,
        )

        events.update { event -> event + newEvent }

        return newEvent.id
    }

    fun changeEvent(id: EventId, info: EventInfo) {
        events.update { communities ->
            communities.map { event ->
                if (event.id == id) {
                    event.copy(
                        name = info.name, description = info.description, avatar = info.avatar,
                    )
                } else {
                    event
                }
            }
        }
    }

    fun getEvent(id: EventId): EventModel? {
        return events.value.firstOrNull { event -> event.id == id }
    }

    fun deleteEvent(id: EventId) {
        events.update { events -> events.filterNot { event -> event.id == id } }
    }


    fun deleteEvents(id: CommunityId) {
        events.update { events -> events.filterNot { event -> event.community == id } }
    }
}