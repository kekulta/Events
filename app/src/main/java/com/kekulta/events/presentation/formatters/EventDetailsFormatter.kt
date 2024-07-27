package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.base.EventModel
import com.kekulta.events.domain.models.base.UserModel
import com.kekulta.events.domain.models.pagination.Page
import com.kekulta.events.presentation.ui.models.EventDetailsVo
import com.kekulta.events.presentation.ui.models.VisitorVo
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char

class EventDetailsFormatter {
    fun format(
        event: EventModel,
        users: Page<UserModel>,
        isAttending: Boolean,
        isLoggedIn: Boolean,
        format: DateTimeFormat<LocalDateTime> = DateFormat,
    ): EventDetailsVo {
        val visitors =
            Page(
                values = users.map { user -> VisitorVo(id = user.id, avatar = user.avatar) },
                offset = users.offset,
                total = users.total,
            )

        return EventDetailsVo(
            name = event.name,
            description = event.description,
            date = event.date.format(format),
            location = event.location.location,
            tags = event.tags,
            // Will be changed when actual map introduced
            mapUrl = "https://i.ibb.co/Lphf2PK/map.jpg",
            visitors = visitors,
            isAttending = isAttending,
            isAbleToRegister = isLoggedIn,
        )
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