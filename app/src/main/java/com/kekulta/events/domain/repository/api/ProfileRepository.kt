package com.kekulta.events.domain.repository.api

import com.kekulta.events.domain.models.AccessToken
import com.kekulta.events.domain.models.ProfileModel
import kotlinx.coroutines.flow.StateFlow

interface ProfileRepository {
    fun observeCurrentProfile(): StateFlow<ProfileModel?>
    suspend fun getCurrentProfile(): ProfileModel?
    suspend fun sendVerification(number: String): Boolean
    suspend fun checkVerification(number: String, code: String): AccessToken
    suspend fun createProfile(profile: ProfileModel, token: AccessToken): Boolean
}