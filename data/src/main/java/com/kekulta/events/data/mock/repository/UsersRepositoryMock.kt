package com.kekulta.events.data.mock.repository

import com.kekulta.events.data.mock.service.MockUsersService
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.UserModel
import com.kekulta.events.domain.repository.api.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class UsersRepositoryMock(
    private val mockUsersService: MockUsersService,
) : UsersRepository {
    private val users = mockUsersService.fetchUsers()

    override fun observeUser(id: UserId): Flow<UserModel?> {
        return users.map { users -> users.firstOrNull { user -> user.id == id } }
    }

    override fun observeUsers(ids: List<UserId>): Flow<List<UserModel>> {
        val requestedIds = ids.toSet()

        return users.map { users -> users.filter { user -> requestedIds.contains(user.id) } }
    }
}