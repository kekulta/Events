package com.kekulta.events.presentation.ui.models

data class EventDetailsVo(
    val name: String,
    val description: String,
    val dateLocation: String,
    val tags: List<String>,
    val mapUrl: String,
    val attendees: List<AttendeeVo>,
    val isAttending: Boolean,
)