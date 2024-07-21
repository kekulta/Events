package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.UserModel
import com.kekulta.events.presentation.ui.models.VisitorVo
import com.kekulta.events.presentation.ui.models.EventDetailsVo
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char

class EventDetailsFormatter {
    fun format(
        event: EventModel,
        users: List<UserModel>,
        profile: ProfileModel?,
        format: DateTimeFormat<LocalDateTime> = DateFormat,
    ): EventDetailsVo {
        val missingUsersCount = (event.visitors.size - users.size).coerceAtLeast(0)
        val visitors = users.map { user -> VisitorVo(id = user.id, avatar = user.avatar) } + List(
            missingUsersCount
        ) { index ->
            /*
                Every visitor MUST have an unique id!!
             */
            VisitorVo(
                id = UserId("no-user-$index"),
                avatar = Avatar(null)
            )
        }
        val isAttending =
            profile?.id != null && users.firstOrNull { user -> user.id == profile.id } != null

        return EventDetailsVo(
            name = event.name,
            description = event.description,
            date = event.date.format(format),
            location = event.location,
            tags = event.tags,
            mapUrl = event.mapUrl,
            visitors = visitors,
            isAttending = isAttending,
            isAbleToRegister = profile != null,
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