package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.pagination.Page
import kotlinx.coroutines.flow.Flow

interface GetAllEventsInteractor {
    fun execute(offset: Int, limit: Int): Flow<Page<EventModel>>
}
