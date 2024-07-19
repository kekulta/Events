package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.GroupModel
import com.kekulta.events.domain.repository.api.GroupsQuery
import com.kekulta.events.domain.repository.api.GroupsRepository
import kotlinx.coroutines.flow.Flow

class AllGroupsUseCase(
    private val groupsRepository: GroupsRepository,
) {
    fun execute(): Flow<List<GroupModel>> {
        return groupsRepository.observeGroupsForQuery(GroupsQuery.Recommendation(limit = 25))
    }
}