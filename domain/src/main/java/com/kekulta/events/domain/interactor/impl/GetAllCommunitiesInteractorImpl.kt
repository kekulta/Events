package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetAllCommunitiesInteractor
import com.kekulta.events.domain.models.base.CommunityModel
import com.kekulta.events.domain.models.pagination.BASE_PAGE_SIZE
import com.kekulta.events.domain.models.pagination.CommunitiesQuery
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.domain.repository.api.CommunitiesRepository
import kotlinx.coroutines.flow.Flow

internal class GetAllCommunitiesInteractorImpl(
    private val communitiesRepository: CommunitiesRepository,
) : GetAllCommunitiesInteractor {
    override fun execute(offset: Int, limit: Int): Flow<Page<CommunityModel>> {
        return communitiesRepository.observeCommunitiesForQuery(query(offset, limit))
    }

    private fun query(offset: Int, limit: Int) = CommunitiesQuery.Recommendation(
        limit = limit,
        offset = offset,
    )
}