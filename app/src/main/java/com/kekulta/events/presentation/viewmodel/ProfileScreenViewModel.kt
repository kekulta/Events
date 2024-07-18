package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.usecase.CurrentProfileUseCase
import com.kekulta.events.domain.usecase.LogOutUseCase
import com.kekulta.events.presentation.ui.models.ProfileVo
import kotlinx.coroutines.flow.StateFlow

class ProfileScreenViewModel(
    private val currentProfileUseCase: CurrentProfileUseCase,
    private val logOutUseCase: LogOutUseCase,
) : AbstractCoroutineViewModel() {

    fun observeProfileState(): StateFlow<ProfileVo?> = currentProfileUseCase.execute()

    fun logOut(): Boolean = logOutUseCase.execute()
}