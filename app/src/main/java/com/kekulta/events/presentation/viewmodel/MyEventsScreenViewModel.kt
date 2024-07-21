package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.interactor.MyPastEventsInteractor
import com.kekulta.events.domain.interactor.MyPlannedEventsInteractor
import com.kekulta.events.presentation.formatters.EventItemFormatter
import com.kekulta.events.presentation.ui.models.EventItemVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class MyEventsScreenViewModel(
    private val myPastEventsInteractor: MyPastEventsInteractor,
    private val myPlannedEventsInteractor: MyPlannedEventsInteractor,
    private val eventItemFormatter: EventItemFormatter,
) : AbstractCoroutineViewModel() {

    fun observePlannedEvents(): StateFlow<ScreenState<List<EventItemVo>>> =
        myPlannedEventsInteractor.execute()
            .mapLatest { events -> ScreenState.Success(eventItemFormatter.format(events)) }
            .asStateFlow(ScreenState.Loading())

    fun observePastEvents(): StateFlow<ScreenState<List<EventItemVo>>> =
        myPastEventsInteractor.execute()
            .mapLatest { events -> ScreenState.Success(eventItemFormatter.format(events)) }
            .asStateFlow(ScreenState.Loading())
}