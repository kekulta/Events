package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.CommunityModel
import kotlinx.coroutines.flow.Flow

interface GetAllCommunitiesInteractor {
    fun execute(): Flow<List<CommunityModel>>
}
