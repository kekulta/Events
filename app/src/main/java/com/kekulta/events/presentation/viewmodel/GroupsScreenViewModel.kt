package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.usecase.AllGroupsUseCase
import com.kekulta.events.presentation.formatters.GroupItemFormatter
import com.kekulta.events.presentation.ui.models.GroupItemVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class GroupsScreenViewModel(
    private val allGroupsUseCase: AllGroupsUseCase,
    private val groupItemFormatter: GroupItemFormatter,
) : AbstractCoroutineViewModel() {
    fun observeAllGroups(): StateFlow<ScreenState<List<GroupItemVo>>> =
        allGroupsUseCase.execute()
            .mapLatest { groups -> ScreenState.Success(groupItemFormatter.format(groups)) }
            .asStateFlow(ScreenState.Loading())
}