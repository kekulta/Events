package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.usecase.ActiveEventsUseCase
import com.kekulta.events.domain.usecase.AllEventsUseCase
import com.kekulta.events.presentation.formatters.ActiveEventItemFormatter
import com.kekulta.events.presentation.formatters.EventItemFormatter
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
    private val activeEventItemFormatter: ActiveEventItemFormatter,
    private val eventItemVoFormatter: EventItemFormatter,
) : AbstractCoroutineViewModel() {
    fun observeAllEvents(): StateFlow<ScreenState<List<EventItemVo>>> =
        allEventsUseCase.execute().mapLatest { events ->
            ScreenState.Success(
                eventItemVoFormatter.format(
                    events
                )
            )
        }.asStateFlow(ScreenState.Loading())

    fun observeActiveEvents(): StateFlow<ScreenState<List<ActiveEventItemVo>>> =
        activeEventsUseCase.execute().mapLatest { events ->
            ScreenState.Success(
                activeEventItemFormatter.format(events)
            )
        }.asStateFlow(ScreenState.Loading())
}

