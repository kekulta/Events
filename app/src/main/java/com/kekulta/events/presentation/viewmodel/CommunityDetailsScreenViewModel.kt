package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.CommunityId
import com.kekulta.events.domain.interactor.CommunityDetailsInteractor
import com.kekulta.events.presentation.formatters.CommunityDetailsFormatter
import com.kekulta.events.presentation.ui.models.CommunityDetailsVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class CommunityDetailsScreenViewModel(
    private val communityDetailsInteractor: CommunityDetailsInteractor,
    private val communityDetailsFormatter: CommunityDetailsFormatter,
) : AbstractCoroutineViewModel() {
    private val currId = MutableStateFlow<CommunityId?>(null)

    private val state: StateFlow<ScreenState<CommunityDetailsVo>> =
        currId.filterNotNull().flatMapLatest { id -> communityDetailsInteractor.execute(id) }
            .mapLatest { model ->
                val vo = model?.let { modelNotNull -> communityDetailsFormatter.format(modelNotNull) }

                if (vo != null) {
                    ScreenState.Success(vo)
                } else {
                    ScreenState.Error(message = null)
                }
            }.asStateFlow(ScreenState.Loading())

    fun observeState(): StateFlow<ScreenState<CommunityDetailsVo>> = state

    fun setId(communityId: CommunityId) {
        currId.update { communityId }
    }
}
