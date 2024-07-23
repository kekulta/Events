package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.interactor.GetMyPastEventsInteractor
import com.kekulta.events.domain.interactor.GetMyPlannedEventsInteractor
import com.kekulta.events.presentation.formatters.EventItemFormatter
import com.kekulta.events.presentation.ui.models.EventItemVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class MyEventsScreenViewModel(
    private val getMyPastEventsInteractor: GetMyPastEventsInteractor,
    private val getMyPlannedEventsInteractor: GetMyPlannedEventsInteractor,
    private val eventItemFormatter: EventItemFormatter,
) : AbstractCoroutineViewModel() {

    fun observePlannedEvents(): StateFlow<ScreenState<List<EventItemVo>>> =
        getMyPlannedEventsInteractor.execute()
            .mapLatest { events -> ScreenState.Success(eventItemFormatter.format(events)) }
            .asStateFlow(ScreenState.Loading())

    fun observePastEvents(): StateFlow<ScreenState<List<EventItemVo>>> =
        getMyPastEventsInteractor.execute()
            .mapLatest { events -> ScreenState.Success(eventItemFormatter.format(events)) }
            .asStateFlow(ScreenState.Loading())
}