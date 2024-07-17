package com.kekulta.events.domain.repository.mock

import com.kekulta.events.domain.models.AccessToken
import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.PhoneNumber
import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.repository.api.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileRepositoryMock : ProfileRepository {
    private val profile = MutableStateFlow<ProfileModel?>(
        ProfileModel(
            id = UserId("2"),
            accessToken = MockToken,
            number = PhoneNumber("+7", "9959177242"),
            name = "Ruslan",
            surname = "Russkikh",
            avatar = Avatar(null),
        )
    )

    override fun observeCurrentProfile(): StateFlow<ProfileModel?> = profile.asStateFlow()

    override suspend fun getCurrentProfile(): ProfileModel? = profile.value

    override suspend fun sendVerification(number: String): Boolean {
        return true
    }

    override suspend fun checkVerification(number: String, code: String): AccessToken {
        return MockToken
    }

    override suspend fun createProfile(profile: ProfileModel, token: AccessToken): Boolean {
        this.profile.update { profile }
        return true
    }

    companion object {
        private val MockToken = AccessToken("Mock Token")
    }
}