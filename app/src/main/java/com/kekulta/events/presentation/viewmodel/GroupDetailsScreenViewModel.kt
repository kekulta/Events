package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.GroupId
import com.kekulta.events.domain.usecase.GroupDetailsUseCase
import com.kekulta.events.presentation.formatters.GroupDetailsFormatter
import com.kekulta.events.presentation.ui.models.GroupDetailsVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class GroupDetailsScreenViewModel(
    private val groupDetailsUseCase: GroupDetailsUseCase,
    private val groupDetailsFormatter: GroupDetailsFormatter,
) : AbstractCoroutineViewModel() {
    private val currId = MutableStateFlow<GroupId?>(null)

    private val state: StateFlow<ScreenState<GroupDetailsVo>> =
        currId.filterNotNull().flatMapLatest { id -> groupDetailsUseCase.execute(id) }
            .mapLatest { model ->
                val vo = model?.let { modelNotNull -> groupDetailsFormatter.format(modelNotNull) }

                if (vo != null) {
                    ScreenState.Success(vo)
                } else {
                    ScreenState.Error("Something went wrong!")
                }
            }.asStateFlow(ScreenState.Loading())

    fun observeState(): StateFlow<ScreenState<GroupDetailsVo>> = state

    fun setId(groupId: GroupId) {
        currId.update { groupId }
    }
}
