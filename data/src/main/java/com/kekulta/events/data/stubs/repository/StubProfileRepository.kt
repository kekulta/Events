package com.kekulta.events.data.stubs.repository

import com.kekulta.events.common.utils.stateMapLatest
import com.kekulta.events.data.stubs.service.StubAuthService
import com.kekulta.events.data.stubs.service.StubUsersService
import com.kekulta.events.domain.models.base.ProfileModel
import com.kekulta.events.domain.models.info.PersonalInfo
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.repository.api.ProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
internal class StubProfileRepository(
    private val stubAuthService: StubAuthService,
    private val stubUsersService: StubUsersService,
) : ProfileRepository {
    private val authStatus: StateFlow<AuthStatus> = stubAuthService.observeAuthStatus()

    private val currentProfile =
        authStatus.stateMapLatest { auth -> (auth as? AuthStatus.Authorized)?.id }

    override fun observeCurrentProfile(): StateFlow<ProfileModel?> =
        currentProfile.stateMapLatest { id -> id?.let { stubUsersService.getProfile(it) } }

    override fun changeProfile(info: PersonalInfo) {
        val currentId = currentProfile.value ?: return

        stubUsersService.changeProfile(currentId, info)
    }
}