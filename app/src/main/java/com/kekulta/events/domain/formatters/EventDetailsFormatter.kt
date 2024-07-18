package com.kekulta.events.domain.formatters

import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.EventModel
import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.models.UserId
import com.kekulta.events.domain.models.UserModel
import com.kekulta.events.presentation.ui.models.AttendeeVo
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
        val missingUsersCount = (event.attendees.size - users.size).coerceAtLeast(0)
        val attendees =
            users.map { user -> AttendeeVo(id = user.id, avatar = user.avatar) } + List(
                missingUsersCount
            ) { AttendeeVo(id = UserId("no-user"), avatar = Avatar(null)) }
        val isAttending =
            profile?.id != null && users.firstOrNull { user -> user.id == profile.id } != null

        return EventDetailsVo(
            name = event.name,
            description = event.description,
            date = event.date.format(format),
            location = event.location,
            tags = event.tags,
            mapUrl = event.mapUrl,
            attendees = attendees,
            isAttending = isAttending,
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