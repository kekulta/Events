package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.repository.api.EventStatus
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalCoroutinesApi::class)
class MyPastEventsUseCase(
    private val profileRepository: ProfileRepository,
    private val eventsRepository: EventsRepository,
) {
    fun execute(): Flow<List<EventModel>> {
        return profileRepository.observeCurrentProfile().flatMapLatest { profile ->
            if (profile != null) {
                eventsRepository.observeEventsForQuery(
                    query = userQuery(profile.id)
                )
            } else {
                flow { emit(emptyList()) }
            }
        }
    }

    private fun userQuery(id: UserId): EventsQuery.User {
        return EventsQuery.User(
            id = id,
            limit = 25,
            statusList = listOf(EventStatus.PAST)
        )
    }
}