package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.interactor.GetCommunityEventsInteractor
import com.kekulta.events.domain.interactor.GetCommunityInteractor
import com.kekulta.events.domain.models.id.CommunityId
import com.kekulta.events.domain.models.pagination.BASE_PAGE_SIZE
import com.kekulta.events.presentation.formatters.CommunityDetailsFormatter
import com.kekulta.events.presentation.ui.models.CommunityDetailsVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class CommunityDetailsScreenViewModel(
    private val getCommunityInteractor: GetCommunityInteractor,
    private val getCommunityEventsInteractor: GetCommunityEventsInteractor,
    private val communityDetailsFormatter: CommunityDetailsFormatter,
) : AbstractCoroutineViewModel() {
    private val currId = MutableStateFlow<CommunityId?>(null)

    private val state: StateFlow<ScreenState<CommunityDetailsVo>> =
        currId.filterNotNull().flatMapLatest { id ->
            combine(
                getCommunityInteractor.execute(id),
                getCommunityEventsInteractor.execute(id, 0, BASE_PAGE_SIZE),
            ) { community, events ->
                val vo = community?.let { communityDetailsFormatter.format(community, events) }

                if (vo != null) {
                    ScreenState.Success(vo)
                } else {
                    ScreenState.Error(message = null)
                }
            }
        }.asStateFlow(ScreenState.Loading())

    fun observeState(): StateFlow<ScreenState<CommunityDetailsVo>> = state

    fun setId(communityId: CommunityId) {
        currId.update { communityId }
    }
}
