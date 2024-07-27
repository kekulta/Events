package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.IsSubscribedInteractor
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.pagination.EventsQuery
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.repository.api.AuthRepository
import com.kekulta.events.domain.repository.api.EventsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
class IsSubscribedInteractorImpl(
    private val authRepository: AuthRepository,
    private val eventsRepository: EventsRepository,
) : IsSubscribedInteractor {
    override fun execute(id: EventId): Flow<Boolean> =
        authRepository.observeAuthStatus().flatMapLatest { status ->
            (status as? AuthStatus.Authorized)?.id?.let { userId ->
                eventsRepository.observeEventsForQuery(
                    query(userId, id)
                )
            }?.map { events -> events.isNotEmpty() } ?: flow { emit(false) }
        }


    private fun query(userId: UserId, eventId: EventId) = EventsQuery.Subscription(
        userId = userId,
        eventId = eventId,
    )
}