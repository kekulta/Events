package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.base.UserModel
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.domain.models.pagination.UsersQuery
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun observeUsersForQuery(query: UsersQuery): Flow<Page<UserModel>>
}