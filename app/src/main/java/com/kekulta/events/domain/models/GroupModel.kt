package com.kekulta.events.domain.models

data class GroupModel(
    val id: GroupId, val name: String, val avatar: Avatar, val members: List<UserId>
)