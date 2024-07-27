package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetCommunityMembersInteractor
import com.kekulta.events.domain.models.base.UserModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.domain.models.pagination.UsersQuery
import com.kekulta.events.domain.models.status.EventStatus
import com.kekulta.events.domain.repository.api.UsersRepository
import kotlinx.coroutines.flow.Flow

internal class GetCommunityMembersInteractorImpl(
    private val usersRepository: UsersRepository,
) : GetCommunityMembersInteractor {
    override fun execute(id: CommunityId, offset: Int, limit: Int): Flow<Page<UserModel>> =
        usersRepository.observeUsersForQuery(query(id, offset, limit))


    private fun query(id: CommunityId, offset: Int, limit: Int) = UsersQuery.Community(
        id = id,
        statusList = listOf(EventStatus.ANY),
        limit = limit,
        offset = offset,
    )
}

