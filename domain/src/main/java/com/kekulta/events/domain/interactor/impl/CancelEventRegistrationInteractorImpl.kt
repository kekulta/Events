package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.CancelEventRegistrationInteractor
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CancelEventRegistrationInteractorImpl(
    private val eventsRepository: EventsRepository,
) : CancelEventRegistrationInteractor {

    override suspend fun execute(id: EventId) = withContext(Dispatchers.IO) {
        eventsRepository.cancelRegistration(id)
    }
}