package com.kekulta.events.presentation.ui.models

data class GroupElementVo(
    val id: String,
    val name: String,
    val avatar: String?,
    /* TODO: Add plurals */
    val members: String,
)

