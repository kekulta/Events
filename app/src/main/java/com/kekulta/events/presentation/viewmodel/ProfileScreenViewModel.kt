package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.usecase.CurrentProfileDetailsUseCase
import com.kekulta.events.domain.usecase.LogOutUseCase
import com.kekulta.events.presentation.ui.models.ProfileDetailsVo
import kotlinx.coroutines.flow.StateFlow

class ProfileScreenViewModel(
    private val currentProfileDetailsUseCase: CurrentProfileDetailsUseCase,
    private val logOutUseCase: LogOutUseCase,
) : AbstractCoroutineViewModel() {

    fun observeProfileState(): StateFlow<ProfileDetailsVo?> = currentProfileDetailsUseCase.execute()

    fun logOut(): Boolean = logOutUseCase.execute()
}