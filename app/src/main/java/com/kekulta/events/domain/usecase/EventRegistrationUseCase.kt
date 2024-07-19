package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRegistrationUseCase(
    private val profileRepository: ProfileRepository,
    private val eventsRepository: EventsRepository,
) {
    /*
        I do not use "one usecase - one method" contract.
        I trade some architectural purity to convenience here.
     */
    suspend fun register(id: EventId): Boolean {
        return withContext(Dispatchers.IO) {
            val userId = profileRepository.getCurrentProfile()?.id
            return@withContext userId?.let { eventsRepository.registerForEvent(id, it) } ?: false
        }
    }

    suspend fun cancel(id: EventId): Boolean {
        return withContext(Dispatchers.IO) {
            val userId = profileRepository.getCurrentProfile()?.id
            return@withContext userId?.let { eventsRepository.cancelRegistration(id, it) } ?: false
        }
    }
}