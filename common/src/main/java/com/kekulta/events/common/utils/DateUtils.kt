package com.kekulta.events.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Check if the given date is the today date in the given [timeZone]
 */
fun LocalDate.isToday(timeZone: TimeZone = TimeZone.currentSystemDefault()): Boolean {
    return this == Clock.System.now().toLocalDateTime(timeZone).date
}

/**
 * Check if the given date is in the past in the given [timeZone]
 */
fun LocalDate.isPast(timeZone: TimeZone = TimeZone.currentSystemDefault()): Boolean {
    return this < Clock.System.now().toLocalDateTime(timeZone).date
}

/**
 * Check if the given date is in the future in the given [timeZone]
 */
fun LocalDate.isFuture(timeZone: TimeZone = TimeZone.currentSystemDefault()): Boolean {
    return this > Clock.System.now().toLocalDateTime(timeZone).date
}
