package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.CommunityDetailsModel
import com.kekulta.events.domain.models.CommunityId
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.CommunitiesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class CommunityDetailsInteractor(
    private val eventsRepository: EventsRepository,
    private val communitiesRepository: CommunitiesRepository,
) {
    fun execute(id: CommunityId): Flow<CommunityDetailsModel?> {
        return communitiesRepository.observeCommunity(id).flatMapLatest { community ->
            if (community != null) {
                eventsRepository.observeEvents(community.events).mapLatest { events ->

                    CommunityDetailsModel(
                        community = community,
                        events = events,
                    )
                }
            } else {
                flow<CommunityDetailsModel?> { emit(null) }
            }
        }
    }
}