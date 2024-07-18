package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.VerificationCode
import com.kekulta.events.domain.usecase.CheckCodeUseCase
import com.kekulta.events.domain.usecase.CurrentAuthStatusUseCase
import com.kekulta.events.domain.usecase.LogOutUseCase
import kotlinx.coroutines.flow.StateFlow

class EnterCodeScreenViewModel(
    private val currentAuthStatusUseCase: CurrentAuthStatusUseCase,
    private val checkCodeUseCase: CheckCodeUseCase,
    private val logOutUseCase: LogOutUseCase,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = currentAuthStatusUseCase.execute()
    fun checkCode(code: VerificationCode): Boolean = checkCodeUseCase.execute(code)
    fun logOut(): Boolean = logOutUseCase.execute()
}