package com.kekulta.events.data.mock.repository

import com.kekulta.events.common.utils.stateMapLatest
import com.kekulta.events.data.mock.service.MockAuthService
import com.kekulta.events.data.mock.service.MockUsersService
import com.kekulta.events.domain.models.base.ProfileModel
import com.kekulta.events.domain.models.info.PersonalInfo
import com.kekulta.events.domain.models.status.AuthStatus
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
        authStatus.stateMapLatest { auth -> (auth as? AuthStatus.Authorized)?.id }

    override fun observeCurrentProfile(): StateFlow<ProfileModel?> =
        currentProfile.stateMapLatest { id -> id?.let { mockUsersService.getProfile(it) } }

    override fun changeProfile(info: PersonalInfo) {
        val currentId = currentProfile.value ?: return

        mockUsersService.changeProfile(currentId, info)
    }
}