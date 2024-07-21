package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.CommunityId
import com.kekulta.events.domain.models.CommunityModel
import kotlinx.coroutines.flow.Flow

interface CommunitiesRepository {
    fun observeCommunitiesForQuery(query: CommunitiesQuery): Flow<List<CommunityModel>>
    fun observeCommunity(id: CommunityId): Flow<CommunityModel?>
    suspend fun joinCommunity(id: CommunityId, userId: UserId): Boolean
    suspend fun leaveCommunity(id: CommunityId, userId: UserId): Boolean
}

sealed interface CommunitiesQuery {
    val limit: Int

    data class Search(
        val query: String, override val limit: Int,
    ) : CommunitiesQuery

    data class Recommendation(
        override val limit: Int,
    ) : CommunitiesQuery


    data class User(
        val id: UserId, override val limit: Int,
    ) : CommunitiesQuery
}
