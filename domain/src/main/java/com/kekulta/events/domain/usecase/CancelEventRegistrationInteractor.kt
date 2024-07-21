package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CancelEventRegistrationInteractor(
    private val profileRepository: ProfileRepository,
    private val eventsRepository: EventsRepository,
) {

    suspend fun execute(id: EventId): Boolean {
        return withContext(Dispatchers.IO) {
            val userId = profileRepository.getCurrentProfile()?.id
            return@withContext userId?.let { eventsRepository.cancelRegistration(id, it) } ?: false
        }
    }
}