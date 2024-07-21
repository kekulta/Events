package com.kekulta.events.presentation.ui.models

data class EventDetailsVo(
    val name: String,
    val description: String,
    val date: String,
    val location: String,
    val tags: List<String>,
    val mapUrl: String,
    val visitors: List<VisitorVo>,
    val isAttending: Boolean,
    val isAbleToRegister: Boolean,
)