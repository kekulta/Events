package com.kekulta.events.domain.models

data class GroupDetailsModel(
    val group: GroupModel,
    val events: List<EventModel>
)