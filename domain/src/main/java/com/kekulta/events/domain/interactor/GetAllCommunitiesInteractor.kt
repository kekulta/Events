package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.base.CommunityModel
import com.kekulta.events.domain.models.pagination.BASE_PAGE_SIZE
import com.kekulta.events.domain.models.pagination.Page
import kotlinx.coroutines.flow.Flow

interface GetAllCommunitiesInteractor {
    fun execute(offset: Int, limit: Int): Flow<Page<CommunityModel>>
}
