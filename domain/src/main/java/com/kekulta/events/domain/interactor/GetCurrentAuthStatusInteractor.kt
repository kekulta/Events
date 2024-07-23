package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.AuthStatus
import kotlinx.coroutines.flow.StateFlow

interface GetCurrentAuthStatusInteractor {
    fun execute(): StateFlow<AuthStatus>
}
