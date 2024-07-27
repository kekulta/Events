package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.interactor.CancelEventRegistrationInteractor
import com.kekulta.events.domain.interactor.GetCurrentProfileInteractor
import com.kekulta.events.domain.interactor.GetEventInteractor
import com.kekulta.events.domain.interactor.GetEventVisitorsInteractor
import com.kekulta.events.domain.interactor.IsRegisteredToEventInteractor
import com.kekulta.events.domain.interactor.RegisterToEventInteractor
import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.pagination.BASE_PAGE_SIZE
import com.kekulta.events.presentation.formatters.EventDetailsFormatter
import com.kekulta.events.presentation.ui.models.EventDetailsVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class EventDetailsScreenViewModel(
    private val getEventInteractor: GetEventInteractor,
    private val getEventVisitorsInteractor: GetEventVisitorsInteractor,
    private val getCurrentProfileInteractor: GetCurrentProfileInteractor,
    private val registerToEventInteractor: RegisterToEventInteractor,
    private val cancelEventRegistrationInteractor: CancelEventRegistrationInteractor,
    private val eventDetailsFormatter: EventDetailsFormatter,
    private val isRegisteredToEventInteractor: IsRegisteredToEventInteractor,
) : AbstractCoroutineViewModel() {
    private val currId = MutableStateFlow<EventId?>(null)

    private val state: StateFlow<ScreenState<EventDetailsVo>> =
        currId.filterNotNull().flatMapLatest { id ->
            combine(
                getEventInteractor.execute(id),
                getEventVisitorsInteractor.execute(id, 0, BASE_PAGE_SIZE),
                getCurrentProfileInteractor.execute(),
                isRegisteredToEventInteractor.execute(id)
            ) { event, users, profile, isSubscribed ->
                val vo =
                    event?.let {
                        eventDetailsFormatter.format(
                            event,
                            users,
                            isSubscribed,
                            profile != null
                        )
                    }

                if (vo != null) {
                    ScreenState.Success(vo)
                } else {
                    ScreenState.Error(message = null)
                }
            }
        }.asStateFlow(ScreenState.Loading())

    fun observeState(): StateFlow<ScreenState<EventDetailsVo>> = state

    fun setId(eventId: EventId) {
        currId.update { eventId }
    }

    fun registerOnEvent() {
        val id = currId.value ?: return
        launchScope {
            registerToEventInteractor.execute(id)
        }
    }

    fun cancelRegistration() {
        val id = currId.value ?: return
        launchScope {
            cancelEventRegistrationInteractor.execute(id)
        }
    }
}

