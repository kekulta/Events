package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.base.CommunityModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.info.CommunityInfo
import com.kekulta.events.domain.models.pagination.CommunitiesQuery
import com.kekulta.events.domain.models.pagination.Page
import kotlinx.coroutines.flow.Flow

interface CommunitiesRepository {
    fun observeCommunitiesForQuery(query: CommunitiesQuery): Flow<Page<CommunityModel>>

    suspend fun joinCommunity(id: CommunityId)
    suspend fun leaveCommunity(id: CommunityId)

    suspend fun createCommunity(info: CommunityInfo)
    suspend fun changeCommunity(id: CommunityId, info: CommunityInfo)
    suspend fun deleteCommunity(id: CommunityId)

    suspend fun kickFromCommunity(id: CommunityId, userId: UserId)
}