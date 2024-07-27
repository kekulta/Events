package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.base.UserModel
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.pagination.BASE_PAGE_SIZE
import com.kekulta.events.domain.models.pagination.Page
import kotlinx.coroutines.flow.Flow

interface GetEventVisitorsInteractor {
    fun execute(id: EventId, offset: Int, limit: Int): Flow<Page<UserModel>>
}
