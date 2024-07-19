package com.kekulta.events.domain.models

data class EventDetailsModel(
    val event: EventModel,
    val attendees: List<UserModel>,
    val currentProfile: ProfileModel?
)