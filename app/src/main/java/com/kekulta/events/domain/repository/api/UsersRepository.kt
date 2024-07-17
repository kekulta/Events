package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun observeUser(id: UserId): Flow<UserModel?>
    fun observeUsers(ids: List<UserId>): Flow<List<UserModel>>
}