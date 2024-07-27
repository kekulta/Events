package com.kekulta.events.data.mock.service

import com.kekulta.events.data.mock.functions.mockCommunityModels
import com.kekulta.events.domain.models.base.CommunityModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.info.CommunityInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

internal class MockCommunityService {
    private var communities = MutableStateFlow(mockCommunityModels(50))

    fun fetchCommunities(): Flow<List<CommunityModel>> {
        return communities
    }

    fun createCommunity(info: CommunityInfo): CommunityId {
        val newCommunity = CommunityModel(
            id = CommunityId(Random.nextInt().toString()),
            name = info.name,
            description = info.description,
            avatar = info.avatar,
        )

        communities.update { community -> community + newCommunity }

        return newCommunity.id
    }

    fun changeCommunity(id: CommunityId, info: CommunityInfo) {
        communities.update { communities ->
            communities.map { community ->
                if (community.id == id) {
                    community.copy(
                        name = info.name, description = info.description, avatar = info.avatar,
                    )
                } else {
                    community
                }
            }
        }
    }

    fun getCommunity(id: CommunityId): CommunityModel? {
        return communities.value.firstOrNull { community -> community.id == id }
    }

    fun deleteCommunity(id: CommunityId) {
        communities.update { communities -> communities.filterNot { community -> community.id == id } }
    }
}