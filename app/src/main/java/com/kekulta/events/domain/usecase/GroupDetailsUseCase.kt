package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.formatters.EventItemVoFormatter
import com.kekulta.events.domain.models.GroupId
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.GroupsRepository
import com.kekulta.events.presentation.ui.models.GroupDetailsVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class GroupDetailsUseCase(
    private val eventsRepository: EventsRepository,
    private val groupsRepository: GroupsRepository,
    private val eventItemVoFormatter: EventItemVoFormatter,
) {
    fun execute(id: GroupId): Flow<GroupDetailsVo?> {
        return groupsRepository.observeGroup(id).flatMapLatest { model ->
            if (model != null) {
                eventsRepository.observeEvents(model.events).mapLatest { events ->

                    val eventVos = eventItemVoFormatter.format(events)

                    GroupDetailsVo(
                        id = model.id,
                        name = model.name,
                        description = model.description.takeIf { desc -> !desc.isNullOrBlank() }
                            ?: "Group has no description.",
                        events = eventVos,
                    )
                }
            } else {
                flow<GroupDetailsVo?> { emit(null) }
            }
        }
    }
}