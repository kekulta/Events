package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.values.Identifier

interface SendCodeInteractor {
    suspend fun execute(identifier: Identifier)
}
