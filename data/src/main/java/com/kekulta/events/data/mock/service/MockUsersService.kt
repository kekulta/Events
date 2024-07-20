package com.kekulta.events.data.mock.service

import com.kekulta.events.common.utils.stateMapLatest
import com.kekulta.events.data.mock.functions.mockProfileModels
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.UserInfo
import com.kekulta.events.domain.models.UserModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
internal class MockUsersService {
    private val users = MutableStateFlow(mockProfileModels(50))

    fun fetchUsers(): Flow<List<UserModel>> =
        users.stateMapLatest { profiles ->
            profiles.map { profile ->
                UserModel(
                    id = profile.id,
                    name = profile.info.name,
                    surname = profile.info.surname,
                    avatar = profile.info.avatar,
                )
            }
        }

    fun createUser(userInfo: UserInfo): ProfileModel {
        val newUser = ProfileModel(
            id = UserId(Random.nextInt().toString()),
            number = userInfo.number,
            info = userInfo.info,
        )
        users.update { users ->
            users + newUser
        }

        return newUser
    }

    fun changeProfile(id: UserId, info: PersonalInfo): ProfileModel? {
        return users.updateAndGet { users ->
            val userToChange = users.firstOrNull { user -> user.id == id }

            if (userToChange != null) {
                val newUser = userToChange.copy(info = info)
                users.filter { user -> user.id == id } + newUser
            } else {
                users
            }
        }.firstOrNull { user -> user.id == id }
    }

    fun getProfile(id: UserId): ProfileModel? {
        return users.value.firstOrNull { profile -> profile.id == id }
    }

    fun getProfile(number: PhoneNumber): ProfileModel? {
        return users.value.firstOrNull { profile -> profile.number == number }
    }
}