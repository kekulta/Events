package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.GroupDetailsModel
import com.kekulta.events.domain.models.GroupId
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.GroupsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class GroupDetailsUseCase(
    private val eventsRepository: EventsRepository,
    private val groupsRepository: GroupsRepository,
) {
    fun execute(id: GroupId): Flow<GroupDetailsModel?> {
        return groupsRepository.observeGroup(id).flatMapLatest { group ->
            if (group != null) {
                eventsRepository.observeEvents(group.events).mapLatest { events ->

                    GroupDetailsModel(
                        group = group,
                        events = events,
                    )
                }
            } else {
                flow<GroupDetailsModel?> { emit(null) }
            }
        }
    }
}