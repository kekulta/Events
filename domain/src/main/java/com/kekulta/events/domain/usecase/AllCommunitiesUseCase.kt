package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.models.CommunityModel
import com.kekulta.events.domain.repository.api.CommunitiesQuery
import com.kekulta.events.domain.repository.api.CommunitiesRepository
import kotlinx.coroutines.flow.Flow

class AllCommunitiesUseCase(
    private val communitiesRepository: CommunitiesRepository,
) {
    fun execute(): Flow<List<CommunityModel>> {
        return communitiesRepository.observeCommunitiesForQuery(CommunitiesQuery.Recommendation(limit = 25))
    }
}