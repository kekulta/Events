package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.domain.formatters.ProfileFormatter
import com.kekulta.events.presentation.ui.models.ProfileVo
import com.kekulta.events.utils.stateMapLatest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentProfileUseCase(
    private val profileRepository: ProfileRepository,
    private val profileFormatter: ProfileFormatter,
) {
    fun execute(): StateFlow<ProfileVo?> {
        return profileRepository.observeCurrentProfile().stateMapLatest { model ->
            model?.let { modelNotNull ->
                profileFormatter.format(
                    modelNotNull
                )
            }
        }
    }
}