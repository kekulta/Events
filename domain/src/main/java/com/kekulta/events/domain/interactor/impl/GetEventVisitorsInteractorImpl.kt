package com.kekulta.events.domain.interactor.impl

import com.kekulta.events.domain.interactor.GetEventVisitorsInteractor
import com.kekulta.events.domain.models.base.UserModel
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.domain.models.pagination.UsersQuery
import com.kekulta.events.domain.repository.api.UsersRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventVisitorsInteractorImpl(
    private val usersRepository: UsersRepository,
) : GetEventVisitorsInteractor {
    override fun execute(id: EventId, offset: Int, limit: Int): Flow<Page<UserModel>> =
        usersRepository.observeUsersForQuery(query(id, offset, limit))


    private fun query(id: EventId, offset: Int, limit: Int) = UsersQuery.Event(
        id = id,
        limit = limit,
        offset = offset,
    )
}