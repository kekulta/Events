package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.common.utils.stateMapLatest
import com.kekulta.events.domain.interactor.CurrentProfileInteractor
import com.kekulta.events.presentation.formatters.ProfileItemFormatter
import com.kekulta.events.presentation.ui.models.ProfileItemVo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
class MoreScreenViewModel(
    private val currentProfileInteractor: CurrentProfileInteractor,
    private val profileItemFormatter: ProfileItemFormatter,
) : AbstractCoroutineViewModel() {
    fun observeState(): StateFlow<ProfileItemVo?> =
        currentProfileInteractor.execute().stateMapLatest { profile ->
            profile?.let { profileNotNull ->
                profileItemFormatter.format(profileNotNull)
            }
        }
}