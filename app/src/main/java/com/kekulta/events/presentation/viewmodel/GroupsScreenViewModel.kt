package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.usecase.AllGroupsUseCase
import com.kekulta.events.presentation.ui.models.GroupItemVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class GroupsScreenViewModel(
    private val allGroupsUseCase: AllGroupsUseCase,
) : AbstractCoroutineViewModel() {
    fun observeAllGroups(): StateFlow<ScreenState<List<GroupItemVo>>> =
        allGroupsUseCase.execute().mapLatest { groups -> ScreenState.Success(groups) }
            .asStateFlow(ScreenState.Loading())
}