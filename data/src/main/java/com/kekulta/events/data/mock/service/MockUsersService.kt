package com.kekulta.events.data.mock.service

import com.kekulta.events.data.mock.functions.mockProfileModels
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.UserInfo
import com.kekulta.events.domain.models.UserModel
import kotlin.random.Random

internal class MockUsersService {
    private var users = mockProfileModels(50)

    fun fetchUsers(): List<UserModel> {
        return users.map { profile ->
            UserModel(
                id = profile.id,
                name = profile.info.name,
                surname = profile.info.surname,
                avatar = profile.info.avatar,
            )
        }
    }

    fun createProfile(userInfo: UserInfo): ProfileModel {
        val newUser = ProfileModel(
            id = UserId(Random.nextInt().toString()),
            number = userInfo.number,
            info = userInfo.info,
        )

        users = users + newUser

        return newUser
    }

    fun changeProfile(id: UserId, info: PersonalInfo): ProfileModel? {
        var changedUser: ProfileModel? = null

        users = users.map { user ->
            if (user.id == id) {
                user.copy(info = info).also { changedUser = it }
            } else {
                user
            }
        }

        return changedUser
    }

    fun getProfile(id: UserId): ProfileModel? {
        return users.firstOrNull { profile -> profile.id == id }
    }

    fun getProfile(number: PhoneNumber): ProfileModel? {
        return users.firstOrNull { profile -> profile.number == number }
    }
}