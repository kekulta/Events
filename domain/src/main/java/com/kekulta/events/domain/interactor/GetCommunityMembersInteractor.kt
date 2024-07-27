package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.base.UserModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.pagination.Page
import kotlinx.coroutines.flow.Flow

interface GetCommunityMembersInteractor {
    fun execute(id: CommunityId, offset: Int, limit: Int): Flow<Page<UserModel>>
}