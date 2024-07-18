package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.repository.api.EventType
import com.kekulta.events.domain.repository.api.EventsQuery
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.mock.isPast
import com.kekulta.events.presentation.ui.models.ScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.char

class ActiveEventItemVoFormatter() {
    fun format(models: List<EventModel>): List<ActiveEventItemVo> {
        return models.map { model ->
            ActiveEventItemVo(
                id = model.id,
                name = model.name,
                date = model.date.format(DateFormat),
                location = model.location,
                tags = model.tags,
                avatar = model.avatarUrl,
            )
        }
    }


    companion object {
        private val DateFormat = LocalDateTime.Format {
            dayOfMonth()
            char('.')
            monthNumber()
            char('.')
            year()
        }
    }
}

class EventItemVoFormatter() {
    fun format(models: List<EventModel>): List<EventItemVo> {
        return models.map { model ->
            EventItemVo(
                id = model.id,
                name = model.name,
                date = model.date.format(DateFormat),
                location = model.location,
                tags = model.tags,
                avatar = model.avatarUrl,
                isPast = model.date.date.isPast()
            )
        }
    }


    companion object {
        /*
            I'm not sure if this format should be shared across application or it is
            individual responsibility of the class to have this information
         */
        private val DateFormat = LocalDateTime.Format {
            dayOfMonth()
            char('.')
            monthNumber()
            char('.')
            year()
        }
    }
}

class ActiveEventsUseCase(
    private val eventsRepository: EventsRepository,
    private val activeEventItemVoFormatter: ActiveEventItemVoFormatter
) {
    fun execute(): Flow<List<ActiveEventItemVo>> {
        return eventsRepository.observeEventsForQuery(query)
            .map { events -> activeEventItemVoFormatter.format(events) }
    }

    companion object {
        private val query = EventsQuery.Recommendation(
            types = listOf(EventType.ACTIVE),
            limit = 25,
        )
    }
}

class AllEventsUseCase(
    private val eventsRepository: EventsRepository,
    private val eventItemVoFormatter: EventItemVoFormatter,
) {
    fun execute(): Flow<List<EventItemVo>> {
        return eventsRepository.observeEventsForQuery(query)
            .map { events -> eventItemVoFormatter.format(events) }
    }

    companion object {
        private val query = EventsQuery.Recommendation(
            types = listOf(EventType.ACTIVE, EventType.PAST, EventType.FUTURE),
            limit = 25,
        )
    }
}

class EventsScreenViewModel(
    private val allEventsUseCase: AllEventsUseCase,
    private val activeEventsUseCase: ActiveEventsUseCase,
) : AbstractCoroutineViewModel() {
    fun observeAllEvents(): StateFlow<ScreenState<List<EventItemVo>>> =
        allEventsUseCase.execute().map { events -> ScreenState.Success(events) }
            .asStateFlow(ScreenState.Loading())

    fun observeActiveEvents(): StateFlow<ScreenState<List<ActiveEventItemVo>>> =
        activeEventsUseCase.execute().map { events -> ScreenState.Success(events) }
            .asStateFlow(ScreenState.Loading())
}

data class EventItemVo(
    val id: EventId,
    val name: String,
    val date: String,
    val location: String,
    val tags: List<String>,
    val avatar: Avatar,
    val isPast: Boolean,
)

data class ActiveEventItemVo(
    val id: EventId,
    val name: String,
    val date: String,
    val location: String,
    val tags: List<String>,
    val avatar: Avatar,
)
