package com.kekulta.events.data.mock.repository

import com.kekulta.events.data.mock.functions.mockCommunityModels
import com.kekulta.events.domain.models.CommunityId
import com.kekulta.events.domain.models.CommunityModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.repository.api.CommunitiesQuery
import com.kekulta.events.domain.repository.api.CommunitiesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
internal class CommunitiesRepositoryMock : CommunitiesRepository {
    private val communitiesMap =
        mockCommunityModels(25).associateBy { event -> event.id }.toMutableMap()

    // Set would probably work better but lets not overengineer
    private val communitiesFlow = MutableStateFlow(communitiesMap.values.toList())

    override fun observeCommunitiesForQuery(query: CommunitiesQuery): Flow<List<CommunityModel>> {
        return when (query) {
            is CommunitiesQuery.Recommendation -> {
                communitiesFlow.mapLatest { events ->
                    events.take(query.limit)
                }
            }

            is CommunitiesQuery.Search -> throw NotImplementedError("Search is not implemented!")

            is CommunitiesQuery.User -> {
                communitiesFlow.mapLatest { communities ->
                    communities.filter { event ->
                        event.members.contains(
                            query.id
                        )
                    }
                }
            }
        }
    }

    override fun observeCommunity(id: CommunityId): Flow<CommunityModel?> {
        return communitiesFlow.map { communities -> communities.firstOrNull { community -> community.id == id } }
    }

    override suspend fun joinCommunity(id: CommunityId, userId: UserId): Boolean {
        val community = communitiesMap[id] ?: return false
        val isRegistered = community.members.contains(userId)
        if (isRegistered) {
            return false
        }
        communitiesMap[id] = community.copy(members = community.members + userId)
        communitiesFlow.update { communitiesMap.values.toList() }

        return true
    }

    override suspend fun leaveCommunity(id: CommunityId, userId: UserId): Boolean {
        val community = communitiesMap[id] ?: return false
        val members = community.members.filterNot { user -> user.id == userId.id }
        if (members.size == community.members.size) {
            return false
        }
        communitiesMap[id] = community.copy(members = members)
        communitiesFlow.update { communitiesMap.values.toList() }

        return true
    }
}
