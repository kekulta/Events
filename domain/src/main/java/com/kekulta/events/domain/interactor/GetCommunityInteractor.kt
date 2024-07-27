package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.base.CommunityModel
import com.kekulta.events.domain.models.id.CommunityId
import kotlinx.coroutines.flow.Flow

interface GetCommunityInteractor {
    fun execute(id: CommunityId): Flow<CommunityModel?>
}
