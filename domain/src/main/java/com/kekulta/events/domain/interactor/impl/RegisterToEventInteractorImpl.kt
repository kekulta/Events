package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.RegisterToEventInteractor
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RegisterToEventInteractorImpl(
    private val eventsRepository: EventsRepository,
) : RegisterToEventInteractor {
    override suspend fun execute(id: EventId) = withContext(Dispatchers.IO) {
        eventsRepository.registerForEvent(id)
    }
}