package com.kekulta.events.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDate.isToday(timeZone: TimeZone = TimeZone.currentSystemDefault()): Boolean {
    return this == Clock.System.now().toLocalDateTime(timeZone).date
}

fun LocalDate.isPast(timeZone: TimeZone = TimeZone.currentSystemDefault()): Boolean {
    return this < Clock.System.now().toLocalDateTime(timeZone).date
}

fun LocalDate.isFuture(timeZone: TimeZone = TimeZone.currentSystemDefault()): Boolean {
    return this > Clock.System.now().toLocalDateTime(timeZone).date
}
