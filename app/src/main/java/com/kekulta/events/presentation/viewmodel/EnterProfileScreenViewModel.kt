package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.interactor.GetCurrentAuthStatusInteractor
import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.domain.interactor.RegisterInteractor
import com.kekulta.events.domain.models.info.PersonalInfo
import com.kekulta.events.domain.models.status.AuthStatus
import kotlinx.coroutines.flow.StateFlow

class EnterProfileScreenViewModel(
    private val getCurrentAuthStatusInteractor: GetCurrentAuthStatusInteractor,
    private val registerInteractor: RegisterInteractor,
    private val logOutInteractor: LogOutInteractor,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = getCurrentAuthStatusInteractor.execute()
    fun register(info: PersonalInfo) = launchScope { registerInteractor.execute(info) }
    fun logOut() = launchScope { logOutInteractor.execute() }
}