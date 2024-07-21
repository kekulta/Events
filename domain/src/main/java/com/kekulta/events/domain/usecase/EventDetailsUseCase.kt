package com.kekulta.events.domain.usecase

import com.kekulta.events.common.utils.flattenLatest
import com.kekulta.events.domain.models.EventDetailsModel
import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.domain.repository.api.UsersRepository
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
) {
    fun execute(
        id: EventId,
    ): Flow<EventDetailsModel?> {
        return combine(
            profileRepository.observeCurrentProfile(), eventsRepository.observeEvent(id)
        ) { profile, event ->
            if (event != null) {
                usersRepository.observeUsers(event.visitors).mapLatest { users ->
                    EventDetailsModel(event, users, profile)
                }
            } else {
                flow<EventDetailsModel?> { emit(null) }
            }
        }.flattenLatest()
    }
}