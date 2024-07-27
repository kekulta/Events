package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetCommunityInteractor
import com.kekulta.events.domain.models.base.CommunityModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.pagination.CommunitiesQuery
import com.kekulta.events.domain.repository.api.CommunitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class GetCommunityInteractorImpl(
    private val communitiesRepository: CommunitiesRepository,
) : GetCommunityInteractor {
    override fun execute(id: CommunityId): Flow<CommunityModel?> {
        return communitiesRepository.observeCommunitiesForQuery(CommunitiesQuery.Community(id))
            .map { page ->
                page.values.firstOrNull()
            }
    }
}