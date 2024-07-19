package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.presentation.ui.models.ActiveEventItemVo
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.char

class ActiveEventItemFormatter() {
    fun format(models: List<EventModel>): List<ActiveEventItemVo> {
        return models.map { model ->
            ActiveEventItemVo(
                id = model.id,
                name = model.name,
                date = model.date.format(DateFormat),
                location = model.location,
                tags = model.tags,
                avatar = model.avatar,
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