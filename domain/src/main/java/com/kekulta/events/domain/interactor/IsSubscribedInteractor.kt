package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.id.EventId
import kotlinx.coroutines.flow.Flow

interface IsSubscribedInteractor {
    fun execute(id: EventId): Flow<Boolean>
}
