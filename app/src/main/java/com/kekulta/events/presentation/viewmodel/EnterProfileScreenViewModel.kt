package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.interactor.CurrentAuthStatusInteractor
import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.domain.interactor.RegisterInteractor
import kotlinx.coroutines.flow.StateFlow

class EnterProfileScreenViewModel(
    private val currentAuthStatusInteractor: CurrentAuthStatusInteractor,
    private val registerInteractor: RegisterInteractor,
    private val logOutInteractor: LogOutInteractor,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = currentAuthStatusInteractor.execute()
    fun register(info: PersonalInfo): Boolean = registerInteractor.execute(info)
    fun logOut(): Boolean = logOutInteractor.execute()
}