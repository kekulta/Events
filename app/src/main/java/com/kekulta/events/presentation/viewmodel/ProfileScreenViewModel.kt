package com.kekulta.events.presentation.viewmodel

import com.kekulta.events.domain.models.Avatar
import com.kekulta.events.domain.models.ProfileModel
import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.presentation.ui.screens.login.format
import com.kekulta.events.utils.stateMapLatest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

data class ProfileVo(
    val name: String,
    val number: String,
    val avatar: Avatar,
)

class ProfileFormatter() {
    fun format(model: ProfileModel): ProfileVo {
        return ProfileVo(
            name = "${model.info.name} ${model.info.surname ?: ""}",
            number = model.number.format(),
            avatar = model.info.avatar,
        )
    }
}

class LogOutUseCase(
    private val profileRepository: ProfileRepository,
) {
    fun execute() = profileRepository.logOut()
}

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

class ProfileScreenViewModel(
    private val currentProfileUseCase: CurrentProfileUseCase,
    private val logOutUseCase: LogOutUseCase,
) : AbstractCoroutineViewModel() {

    fun observeProfileState(): StateFlow<ProfileVo?> = currentProfileUseCase.execute()

    fun logOut(): Boolean = logOutUseCase.execute()
}