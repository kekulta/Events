package com.kekulta.events.presentation.ui.models

data class ProfileVo(val avatar: String?, val name: String, val phone: String) {

    data class Socials(
        val twitter: String?,
        val instagram: String?,
        val linkedin: String?,
        val facebook: String?,
    )
}
