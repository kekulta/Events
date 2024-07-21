package com.kekulta.events.presentation.viewmodel

import androidx.compose.ui.res.stringResource
import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.interactor.CancelEventRegistrationInteractor
import com.kekulta.events.domain.interactor.EventDetailsInteractor
import com.kekulta.events.domain.interactor.RegisterToEventInteractor
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
    private val eventDetailsInteractor: EventDetailsInteractor,
    private val registerToEventInteractor: RegisterToEventInteractor,
    private val cancelEventRegistrationInteractor: CancelEventRegistrationInteractor,
    private val eventDetailsFormatter: EventDetailsFormatter,
) : AbstractCoroutineViewModel() {
    private val currId = MutableStateFlow<EventId?>(null)

    private val state: StateFlow<ScreenState<EventDetailsVo>> =
        currId.filterNotNull().flatMapLatest { id -> eventDetailsInteractor.execute(id) }
            .mapLatest { model ->
                val vo = model?.let { modelNotNull ->
                    eventDetailsFormatter.format(
                        modelNotNull.event, modelNotNull.visitors, modelNotNull.currentProfile
                    )
                }

                if (vo != null) {
                    ScreenState.Success(vo)
                } else {
                    ScreenState.Error(message = null)
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

