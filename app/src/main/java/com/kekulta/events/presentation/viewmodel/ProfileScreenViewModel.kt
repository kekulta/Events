package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.common.utils.stateMapLatest
import com.kekulta.events.domain.interactor.GetCurrentProfileInteractor
import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.presentation.formatters.ProfileDetailsFormatter
import com.kekulta.events.presentation.ui.models.ProfileDetailsVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileScreenViewModel(
    private val getCurrentProfileInteractor: GetCurrentProfileInteractor,
    private val profileDetailsFormatter: ProfileDetailsFormatter,
    private val logOutInteractor: LogOutInteractor,
) : AbstractCoroutineViewModel() {

    fun observeProfileState(): StateFlow<ProfileDetailsVo?> =
        getCurrentProfileInteractor.execute()
            .stateMapLatest { profile ->
                profile?.let { profileNotNull ->
                    profileDetailsFormatter.format(
                        profileNotNull
                    )
                }
            }

    fun logOut() = logOutInteractor.execute()
}