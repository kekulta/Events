package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.interactor.CheckCodeInteractor
import com.kekulta.events.domain.interactor.GetCurrentAuthStatusInteractor
import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.domain.models.status.AuthStatus
import com.kekulta.events.domain.models.values.VerificationCode
import kotlinx.coroutines.flow.StateFlow

class EnterCodeScreenViewModel(
    private val getCurrentAuthStatusInteractor: GetCurrentAuthStatusInteractor,
    private val checkCodeInteractor: CheckCodeInteractor,
    private val logOutInteractor: LogOutInteractor,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = getCurrentAuthStatusInteractor.execute()
    fun checkCode(code: VerificationCode) { launchScope { checkCodeInteractor.execute(code) } }
    fun logOut() = launchScope { logOutInteractor.execute() }
}