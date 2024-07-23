package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.usecase.CancelEventRegistrationUseCase
import com.kekulta.events.domain.usecase.EventDetailsUseCase
import com.kekulta.events.domain.usecase.RegisterToEventUseCase
import com.kekulta.events.presentation.formatters.EventDetailsFormatter
import com.kekulta.events.presentation.ui.models.EventDetailsVo
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class EventDetailsScreenViewModel(
    private val eventDetailsUseCase: EventDetailsUseCase,
    private val registerToEventUseCase: RegisterToEventUseCase,
    private val cancelEventRegistrationUseCase: CancelEventRegistrationUseCase,
    private val eventDetailsFormatter: EventDetailsFormatter,
) : AbstractCoroutineViewModel() {
    private val currId = MutableStateFlow<EventId?>(null)

    private val state: StateFlow<ScreenState<EventDetailsVo>> =
        currId.filterNotNull().flatMapLatest { id -> eventDetailsUseCase.execute(id) }
            .mapLatest { model ->
                val vo = model?.let { modelNotNull ->
                    eventDetailsFormatter.format(
                        modelNotNull.event, modelNotNull.attendees, modelNotNull.currentProfile
                    )
                }

                if (vo != null) {
                    ScreenState.Success(vo)
                } else {
                    ScreenState.Error("Something went wrong!")
                }
            }.asStateFlow(ScreenState.Loading())

    fun observeState(): StateFlow<ScreenState<EventDetailsVo>> = state

    fun setId(eventId: EventId) {
        currId.update { eventId }
    }

    fun registerOnEvent() {
        val id = currId.value ?: return
        launchScope {
            registerToEventUseCase.execute(id)
        }
    }

    fun cancelRegistration() {
        val id = currId.value ?: return
        launchScope {
            cancelEventRegistrationUseCase.execute(id)
        }
    }
}

