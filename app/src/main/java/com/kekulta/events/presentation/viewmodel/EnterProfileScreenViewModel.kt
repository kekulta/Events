package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.interactor.GetCurrentAuthStatusInteractor
import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.domain.interactor.RegisterInteractor
import kotlinx.coroutines.flow.StateFlow

class EnterProfileScreenViewModel(
    private val getCurrentAuthStatusInteractor: GetCurrentAuthStatusInteractor,
    private val registerInteractor: RegisterInteractor,
    private val logOutInteractor: LogOutInteractor,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = getCurrentAuthStatusInteractor.execute()
    fun register(info: PersonalInfo): Boolean = registerInteractor.execute(info)
    fun logOut()= logOutInteractor.execute()
}