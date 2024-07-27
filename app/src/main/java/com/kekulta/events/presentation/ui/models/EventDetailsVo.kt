package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.pagination.Page

data class EventDetailsVo(
    val name: String,
    val description: String,
    val date: String,
    val location: String,
    val tags: List<String>,
    val mapUrl: String,
    val visitors: Page<VisitorVo>,
    val isAttending: Boolean,
    val isAbleToRegister: Boolean,
)