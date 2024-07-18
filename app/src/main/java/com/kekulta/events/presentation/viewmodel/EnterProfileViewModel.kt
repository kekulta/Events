package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.usecase.CurrentAuthStatusUseCase
import com.kekulta.events.domain.usecase.LogOutUseCase
import com.kekulta.events.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.StateFlow

class EnterProfileViewModel(
    private val currentAuthStatusUseCase: CurrentAuthStatusUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logOutUseCase: LogOutUseCase,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = currentAuthStatusUseCase.execute()
    fun register(info: PersonalInfo): Boolean = registerUseCase.execute(info)
    fun logOut(): Boolean = logOutUseCase.execute()
}