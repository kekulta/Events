package com.kekulta.events.data.mock.repository

import com.kekulta.events.common.utils.stateMapLatest
import com.kekulta.events.data.mock.service.MockAuthService
import com.kekulta.events.data.mock.service.MockUsersService
import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.repository.api.ProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
internal class ProfileRepositoryMock(
    private val mockAuthService: MockAuthService,
    private val mockUsersService: MockUsersService,
) : ProfileRepository {
    private val authStatus: StateFlow<AuthStatus> = mockAuthService.observeAuthStatus()

    private val currentProfile =
        authStatus.stateMapLatest { auth -> (auth as? AuthStatus.Authorized)?.profile }

    override fun observeCurrentProfile(): StateFlow<ProfileModel?> = currentProfile

    override fun getCurrentProfile(): ProfileModel? = currentProfile.value

    override fun logOut(): Boolean = mockAuthService.logOut()

    override fun changeProfile(info: PersonalInfo): Boolean {
        val currentId = currentProfile.value?.id ?: return false

        return mockUsersService.changeProfile(currentId, info) != null
    }
}