package com.kekulta.events.domain.formatters

import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.presentation.ui.models.EventItemVo
import com.kekulta.events.utils.isPast
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.char

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