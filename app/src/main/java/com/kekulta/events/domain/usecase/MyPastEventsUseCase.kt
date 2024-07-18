package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.formatters.EventItemVoFormatter
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.repository.api.EventStatus
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.presentation.ui.models.EventItemVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class MyPastEventsUseCase(
    private val profileRepository: ProfileRepository,
    private val eventsRepository: EventsRepository,
    private val eventItemVoFormatter: EventItemVoFormatter,
) {
    fun execute(): Flow<List<EventItemVo>> {
        return profileRepository.observeCurrentProfile().flatMapLatest { profile ->
            if (profile != null) {
                eventsRepository.observeEventsForQuery(
                    query = userQuery(profile.id)
                ).mapLatest { models -> eventItemVoFormatter.format(models) }
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