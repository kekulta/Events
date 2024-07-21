package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.common.utils.stateMapLatest
import com.kekulta.events.domain.interactor.CurrentProfileInteractor
import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.presentation.formatters.ProfileDetailsFormatter
import com.kekulta.events.presentation.ui.models.ProfileDetailsVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileScreenViewModel(
    private val currentProfileInteractor: CurrentProfileInteractor,
    private val profileDetailsFormatter: ProfileDetailsFormatter,
    private val logOutInteractor: LogOutInteractor,
) : AbstractCoroutineViewModel() {

    fun observeProfileState(): StateFlow<ProfileDetailsVo?> =
        currentProfileInteractor.execute()
            .stateMapLatest { profile ->
                profile?.let { profileNotNull ->
                    profileDetailsFormatter.format(
                        profileNotNull
                    )
                }
            }

    fun logOut(): Boolean = logOutInteractor.execute()
}