package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.formatters.format
import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.utils.stateMapLatest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

data class ProfileItemVo(
    val name: String,
    val number: String,
    val avatar: Avatar,
)

class ProfileItemFormatter() {
    fun format(model: ProfileModel): ProfileItemVo {
        return ProfileItemVo(
            name = "${model.info.name} ${model.info.surname ?: ""}",
            number = model.number.format(),
            avatar = model.info.avatar,
        )
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentProfileItemUseCase(
    private val profileRepository: ProfileRepository,
    private val profileItemFormatter: ProfileItemFormatter,
) {
    fun execute(): StateFlow<ProfileItemVo?> {
        return profileRepository.observeCurrentProfile().stateMapLatest { profile ->
            profile?.let { profileNotNull ->
                profileItemFormatter.format(profileNotNull)
            }
        }
    }
}

class MoreScreenViewModel(
    private val currentProfileItemUseCase: CurrentProfileItemUseCase,
) : AbstractCoroutineViewModel() {
    fun observeState(): StateFlow<ProfileItemVo?> = currentProfileItemUseCase.execute()
}