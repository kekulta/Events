package com.kekulta.events.domain.models.values

data class PhoneNumber(
    val countryCode: String,
    val prefix: String,
    val number: String,
)

fun PhoneNumber?.sameNumberAs(other: PhoneNumber?) = this != null && other != null &&
        this.prefix == other.prefix && this.number == other.number
