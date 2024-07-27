package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.base.ProfileModel
import com.kekulta.events.domain.models.info.PersonalInfo
import kotlinx.coroutines.flow.StateFlow

/**
 *  This class is for working with currently logged in profile.
 *
 *  For creating or changing profile look at [AuthRepository].
 */
interface ProfileRepository {
    fun observeCurrentProfile(): StateFlow<ProfileModel?>
    fun changeProfile(info: PersonalInfo)
}