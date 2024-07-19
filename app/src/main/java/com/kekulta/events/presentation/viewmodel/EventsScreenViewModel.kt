package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.usecase.ActiveEventsUseCase
import com.kekulta.events.domain.usecase.AllEventsUseCase
import com.kekulta.events.presentation.ui.models.ActiveEventItemVo
import com.kekulta.events.presentation.ui.models.EventItemVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class EventsScreenViewModel(
    private val allEventsUseCase: AllEventsUseCase,
    private val activeEventsUseCase: ActiveEventsUseCase,
) : AbstractCoroutineViewModel() {
    fun observeAllEvents(): StateFlow<ScreenState<List<EventItemVo>>> =
        allEventsUseCase.execute().mapLatest { events -> ScreenState.Success(events) }
            .asStateFlow(ScreenState.Loading())

    fun observeActiveEvents(): StateFlow<ScreenState<List<ActiveEventItemVo>>> =
        activeEventsUseCase.execute().mapLatest { events -> ScreenState.Success(events) }
            .asStateFlow(ScreenState.Loading())
}

