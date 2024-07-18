package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.formatters.EventDetailsFormatter
import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.domain.repository.api.UsersRepository
import com.kekulta.events.presentation.ui.models.EventDetailsVo
import com.kekulta.events.utils.flattenLatest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class EventDetailsUseCase(
    private val profileRepository: ProfileRepository,
    private val eventsRepository: EventsRepository,
    private val usersRepository: UsersRepository,
    private val eventDetailsFormatter: EventDetailsFormatter,
) {
    fun execute(
        id: EventId,
    ): Flow<EventDetailsVo?> {
        return combine(
            profileRepository.observeCurrentProfile(), eventsRepository.observeEventDetails(id)
        ) { profile, event ->
            if (event != null) {
                usersRepository.observeUsers(event.attendees).mapLatest { users ->
                    eventDetailsFormatter.format(event, users, profile)
                }
            } else {
                flow<EventDetailsVo?> { emit(null) }
            }
        }.flattenLatest()
    }
}