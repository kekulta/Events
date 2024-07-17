package com.kekulta.events.presentation.ui.models

import com.kekulta.events.domain.models.Avatar

data class ProfileVo(val avatar: Avatar, val name: String, val phone: String) {
    data class Socials(
        val twitter: String?,
        val instagram: String?,
        val linkedin: String?,
        val facebook: String?,
    )
}
