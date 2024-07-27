package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.pagination.BASE_PAGE_SIZE
import com.kekulta.events.domain.models.pagination.Page
import kotlinx.coroutines.flow.Flow

interface GetCommunityEventsInteractor {
    fun execute(id: CommunityId, offset: Int, limit: Int): Flow<Page<EventModel>>
}
