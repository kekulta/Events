package com.kekulta.events.domain.repository.mock

import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.repository.mock.functions.mockGroupModels
import com.kekulta.events.domain.models.GroupId
import com.kekulta.events.domain.models.GroupModel
import com.kekulta.events.domain.repository.api.GroupsQuery
import com.kekulta.events.domain.repository.api.GroupsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class GroupsRepositoryMock : GroupsRepository {
    private val groupsMap = mockGroupModels(25).associateBy { event -> event.id }.toMutableMap()

    // Set would probably work better but lets not overengineer
    private val groupsFlow = MutableStateFlow(groupsMap.values.toList())

    override fun observeGroupsForQuery(query: GroupsQuery): Flow<List<GroupModel>> {
        return when (query) {
            is GroupsQuery.Recommendation -> {
                groupsFlow.mapLatest { events ->
                    events.take(query.limit)
                }
            }

            is GroupsQuery.Search -> throw NotImplementedError("Search is not implemented!")

            is GroupsQuery.User -> {
                groupsFlow.mapLatest { groups ->
                    groups.filter { event ->
                        event.members.contains(
                            query.id
                        )
                    }
                }
            }
        }
    }

    override fun observeGroup(id: GroupId): Flow<GroupModel?> {
        return groupsFlow.map { groups -> groups.firstOrNull { group -> group.id == id } }
    }

    override suspend fun joinGroup(id: GroupId, userId: UserId): Boolean {
        val group = groupsMap[id] ?: return false
        val isRegistered = group.members.contains(userId)
        if (isRegistered) {
            return false
        }
        groupsMap[id] = group.copy(members = group.members + userId)
        groupsFlow.update { groupsMap.values.toList() }

        return true
    }

    override suspend fun leaveGroup(id: GroupId, userId: UserId): Boolean {
        val group = groupsMap[id] ?: return false
        val members = group.members.filterNot { user -> user.id == userId.id }
        if (members.size == group.members.size) {
            return false
        }
        groupsMap[id] = group.copy(members = members)
        groupsFlow.update { groupsMap.values.toList() }

        return true
    }
}
