package com.kekulta.events.domain.usecase

import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.domain.formatters.ProfileDetailsFormatter
import com.kekulta.events.presentation.ui.models.ProfileDetailsVo
import com.kekulta.events.utils.stateMapLatest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentProfileDetailsUseCase(
    private val profileRepository: ProfileRepository,
    private val profileDetailsFormatter: ProfileDetailsFormatter,
) {
    fun execute(): StateFlow<ProfileDetailsVo?> {
        return profileRepository.observeCurrentProfile().stateMapLatest { model ->
            model?.let { modelNotNull ->
                profileDetailsFormatter.format(
                    modelNotNull
                )
            }
        }
    }
}