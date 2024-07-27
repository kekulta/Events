package com.kekulta.events.presentation.formatters

import com.kekulta.events.common.utils.isPast
import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.presentation.ui.models.EventItemVo
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.char

class EventItemFormatter() {
    fun format(model: EventModel): EventItemVo {
        return EventItemVo(
            id = model.id,
            name = model.name,
            date = model.date.format(DateFormat),
            location = model.location.location,
            tags = model.tags,
            avatar = model.avatar,
            isPast = model.date.date.isPast(),
        )
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