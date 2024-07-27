package com.kekulta.events.data.mock.service

import com.kekulta.events.data.mock.functions.mockProfileModels
import com.kekulta.events.domain.models.base.ProfileModel
import com.kekulta.events.domain.models.base.UserModel
import com.kekulta.events.domain.models.id.UserId
import com.kekulta.events.domain.models.info.PersonalInfo
import com.kekulta.events.domain.models.values.Identifier
import com.kekulta.events.domain.models.values.includes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlin.random.Random

internal class MockUsersService {
    private var users = MutableStateFlow(mockProfileModels(50))

    fun fetchUsers(): Flow<List<UserModel>> {
        return users.map { users ->
            users.map { user ->
                UserModel(
                    id = user.id,
                    name = user.name,
                    surname = user.surname,
                    avatar = user.avatar,
                    email = null,
                    number = null,
                )
            }
        }
    }

    fun createProfile(info: PersonalInfo, identifier: Identifier): UserId? {
        if (users.value.any { user -> user.identifier.includes(identifier) }) {
            return null
        }

        val newUser = ProfileModel(
            id = UserId(Random.nextInt().toString()),
            identifier = identifier,
            name = info.name,
            surname = info.surname,
            avatar = info.avatar,
        )

        users.update { users -> users + newUser }

        return newUser.id
    }

    fun changeProfile(id: UserId, info: PersonalInfo) {
        users.update { users ->
            users.map { user ->
                if (user.id == id) {
                    user.copy(
                        name = info.name, surname = info.surname, avatar = info.avatar,
                    )
                } else {
                    user
                }
            }
        }
    }

    fun getProfile(id: UserId): ProfileModel? {
        return users.value.firstOrNull { user -> user.id == id }
    }

    fun getProfile(identifier: Identifier): ProfileModel? {
        return users.value.firstOrNull { user -> user.identifier.includes(identifier) }
    }

    fun deleteUser(id: UserId) {
        users.update { users -> users.filterNot { user -> user.id == id } }
    }
}