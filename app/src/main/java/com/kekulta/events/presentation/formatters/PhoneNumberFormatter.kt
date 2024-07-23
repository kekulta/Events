package com.kekulta.events.presentation.formatters

import com.kekulta.events.domain.models.PhoneNumber

fun PhoneNumber.format(): String {
    val builder = StringBuilder()

    builder.append(code)
    builder.append(" (")
    builder.append(number.substring(0, 3))
    builder.append(") ")
    builder.append(number.substring(3, 6))
    builder.append('-')
    builder.append(number.substring(6, 8))
    builder.append('-')
    builder.append(number.substring(8, 10))

    return builder.toString()
}
