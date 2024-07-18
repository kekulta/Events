package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.repository.api.GroupsQuery
import com.kekulta.events.domain.repository.api.GroupsRepository
import com.kekulta.events.presentation.ui.models.GroupItemVo
import com.kekulta.events.domain.formatters.GroupItemVoFormatter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class AllGroupsUseCase(
    private val groupsRepository: GroupsRepository,
    private val groupItemVoFormatter: GroupItemVoFormatter,
) {
    fun execute(): Flow<List<GroupItemVo>> {
        return groupsRepository.observeGroupsForQuery(GroupsQuery.Recommendation(limit = 25))
            .mapLatest { models ->
                groupItemVoFormatter.format(models)
            }
    }
}