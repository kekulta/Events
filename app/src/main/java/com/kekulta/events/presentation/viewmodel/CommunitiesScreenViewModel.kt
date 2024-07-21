package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.interactor.GetAllCommunitiesInteractor
import com.kekulta.events.presentation.formatters.CommunityItemFormatter
import com.kekulta.events.presentation.ui.models.CommunityItemVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class CommunitiesScreenViewModel(
    private val getAllCommunitiesInteractor: GetAllCommunitiesInteractor,
    private val communityItemFormatter: CommunityItemFormatter,
) : AbstractCoroutineViewModel() {
    fun observeAllCommunities(): StateFlow<ScreenState<List<CommunityItemVo>>> =
        getAllCommunitiesInteractor.execute()
            .mapLatest { communities -> ScreenState.Success(communityItemFormatter.format(communities)) }
            .asStateFlow(ScreenState.Loading())
}