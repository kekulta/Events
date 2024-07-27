package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.interactor.GetAllCommunitiesInteractor
import com.kekulta.events.domain.interactor.GetCommunityMembersInteractor
import com.kekulta.events.domain.models.pagination.BASE_PAGE_SIZE
import com.kekulta.events.presentation.formatters.CommunityItemFormatter
import com.kekulta.events.presentation.ui.models.CommunityItemVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class CommunitiesScreenViewModel(
    private val getAllCommunitiesInteractor: GetAllCommunitiesInteractor,
    private val getCommunityMembersInteractor: GetCommunityMembersInteractor,
    private val communityItemFormatter: CommunityItemFormatter,
) : AbstractCoroutineViewModel() {
    fun observeAllCommunities(): StateFlow<ScreenState<List<CommunityItemVo>>> =
        getAllCommunitiesInteractor.execute(0, BASE_PAGE_SIZE).flatMapLatest { communities ->

            combine(
                communities.map { community ->
                    getCommunityMembersInteractor.execute(
                        community.id,
                        0,
                        1
                    )
                }
            ) { members ->
                ScreenState.Success(
                    communityItemFormatter.format(
                        communities,
                        members.map { page -> page.total }
                    )
                )
            }
        }.asStateFlow(ScreenState.Loading())
}