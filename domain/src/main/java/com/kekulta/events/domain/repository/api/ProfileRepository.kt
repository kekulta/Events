package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.PersonalInfo
import com.kekulta.events.domain.models.ProfileModel
import kotlinx.coroutines.flow.StateFlow

interface ProfileRepository {
    fun observeCurrentProfile(): StateFlow<ProfileModel?>
    fun getCurrentProfile(): ProfileModel?
    fun logOut(): Boolean

    /*
        You can't change your id or credentials here.
        This handle is for changing personal information on account you're currently in.
        You should use another repository to login or register account.
     */
    fun changeProfile(info: PersonalInfo): Boolean
}