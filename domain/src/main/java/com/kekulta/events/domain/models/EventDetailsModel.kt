package com.kekulta.events.domain.models

data class EventDetailsModel(
    val event: EventModel,
    val visitors: List<UserModel>,
    val currentProfile: ProfileModel?
)