package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetMyPlannedEventsInteractor
import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.pagination.BASE_PAGE_SIZE
import com.kekulta.events.domain.models.pagination.EventsQuery
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.domain.models.pagination.emptyPage
import com.kekulta.events.domain.models.status.EventStatus
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetMyPlannedEventsInteractorImpl(
    private val profileRepository: ProfileRepository,
    private val eventsRepository: EventsRepository,
) : GetMyPlannedEventsInteractor {
    override fun execute(offset: Int, limit: Int): Flow<Page<EventModel>> {
        return profileRepository.observeCurrentProfile().flatMapLatest { profile ->
            if (profile != null) {
                eventsRepository.observeEventsForQuery(
                    query = query(profile.id, offset, limit)
                )
            } else {
                flow { emit(emptyPage()) }
            }
        }
    }

    private fun query(id: UserId, offset: Int, limit: Int): EventsQuery.User {
        return EventsQuery.User(
            id = id,
            limit = limit,
            offset = offset,
            statusList = listOf(EventStatus.ACTIVE, EventStatus.FUTURE)
        )
    }
}