package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.CommunityDetailsModel
import com.kekulta.events.domain.models.CommunityId
import kotlinx.coroutines.flow.Flow

interface GetCommunityDetailsInteractor {
    fun execute(id: CommunityId): Flow<CommunityDetailsModel?>
}
