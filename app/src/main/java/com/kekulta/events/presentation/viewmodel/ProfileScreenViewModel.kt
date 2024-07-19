package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.common.utils.stateMapLatest
import com.kekulta.events.domain.usecase.CurrentProfileUseCase
import com.kekulta.events.domain.usecase.LogOutUseCase
import com.kekulta.events.presentation.formatters.ProfileDetailsFormatter
import com.kekulta.events.presentation.ui.models.ProfileDetailsVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileScreenViewModel(
    private val currentProfileUseCase: CurrentProfileUseCase,
    private val profileDetailsFormatter: ProfileDetailsFormatter,
    private val logOutUseCase: LogOutUseCase,
) : AbstractCoroutineViewModel() {

    fun observeProfileState(): StateFlow<ProfileDetailsVo?> =
        currentProfileUseCase.execute()
            .stateMapLatest { profile ->
                profile?.let { profileNotNull ->
                    profileDetailsFormatter.format(
                        profileNotNull
                    )
                }
            }

    fun logOut(): Boolean = logOutUseCase.execute()
}