package com.kekulta.events.domain.repository.mock

import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.UserModel
import com.kekulta.events.domain.repository.api.UsersRepository
import com.kekulta.events.domain.repository.mock.functions.mockUsers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class UsersRepositoryMock : UsersRepository {
    private val users = MutableStateFlow(mockUsers(50))

    override fun observeUser(id: UserId): Flow<UserModel?> {
        return users.map { users -> users.firstOrNull { user -> user.id == id } }
    }

    override fun observeUsers(ids: List<UserId>): Flow<List<UserModel>> {
        val requestedIds = ids.toSet()

        return users.map { users -> users.filter { user -> requestedIds.contains(user.id) } }
    }
}