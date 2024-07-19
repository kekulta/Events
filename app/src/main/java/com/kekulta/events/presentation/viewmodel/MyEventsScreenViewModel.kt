package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.usecase.MyPastEventsUseCase
import com.kekulta.events.domain.usecase.MyPlannedEventsUseCase
import com.kekulta.events.presentation.ui.models.EventItemVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class MyEventsScreenViewModel(
    private val myPastEventsUseCase: MyPastEventsUseCase,
    private val myPlannedEventsUseCase: MyPlannedEventsUseCase,
) : AbstractCoroutineViewModel() {

    fun observePlannedEvents(): StateFlow<ScreenState<List<EventItemVo>>> =
        myPlannedEventsUseCase.execute().mapLatest { events -> ScreenState.Success(events) }
            .asStateFlow(ScreenState.Loading())

    fun observePastEvents(): StateFlow<ScreenState<List<EventItemVo>>> =
        myPastEventsUseCase.execute().mapLatest { events -> ScreenState.Success(events) }
            .asStateFlow(ScreenState.Loading())
}