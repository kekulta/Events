package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetAllCommunitiesInteractor
import com.kekulta.events.domain.models.CommunityModel
import com.kekulta.events.domain.repository.api.CommunitiesQuery
import com.kekulta.events.domain.repository.api.CommunitiesRepository
import kotlinx.coroutines.flow.Flow

internal class GetAllCommunitiesInteractorImpl(
    private val communitiesRepository: CommunitiesRepository,
) : GetAllCommunitiesInteractor {
    override fun execute(): Flow<List<CommunityModel>> {
        return communitiesRepository.observeCommunitiesForQuery(
            CommunitiesQuery.Recommendation(
                limit = 25
            )
        )
    }
}