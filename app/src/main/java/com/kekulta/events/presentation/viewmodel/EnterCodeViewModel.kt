package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.AuthStatus
import com.kekulta.events.domain.models.VerificationCode
import com.kekulta.events.domain.usecase.CheckCodeUseCase
import com.kekulta.events.domain.usecase.CurrentAuthStatusUseCase
import kotlinx.coroutines.flow.StateFlow

class EnterCodeViewModel(
    private val currentAuthStatusUseCase: CurrentAuthStatusUseCase,
    private val checkCodeUseCase: CheckCodeUseCase,
) : AbstractCoroutineViewModel() {

    fun observeAuthStatus(): StateFlow<AuthStatus> = currentAuthStatusUseCase.execute()
    fun checkCode(code: VerificationCode): Boolean = checkCodeUseCase.execute(code)
}