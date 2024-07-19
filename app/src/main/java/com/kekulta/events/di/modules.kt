package com.kekulta.events.di

import com.kekulta.events.data.MockAuthService
import com.kekulta.events.data.MockUsersService
import com.kekulta.events.domain.formatters.ActiveEventItemVoFormatter
import com.kekulta.events.domain.formatters.EventDetailsFormatter
import com.kekulta.events.domain.formatters.EventItemVoFormatter
import com.kekulta.events.domain.formatters.GroupItemVoFormatter
import com.kekulta.events.domain.formatters.ProfileDetailsFormatter
import com.kekulta.events.domain.repository.api.AuthRepository
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.GroupsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.domain.repository.api.UsersRepository
import com.kekulta.events.domain.repository.mock.AuthRepositoryMock
import com.kekulta.events.domain.repository.mock.EventsRepositoryMock
import com.kekulta.events.domain.repository.mock.GroupsRepositoryMock
import com.kekulta.events.domain.repository.mock.ProfileRepositoryMock
import com.kekulta.events.domain.repository.mock.UsersRepositoryMock
import com.kekulta.events.domain.usecase.ActiveEventsUseCase
import com.kekulta.events.domain.usecase.AllEventsUseCase
import com.kekulta.events.domain.usecase.AllGroupsUseCase
import com.kekulta.events.domain.usecase.CheckCodeUseCase
import com.kekulta.events.domain.usecase.CurrentAuthStatusUseCase
import com.kekulta.events.domain.usecase.CurrentProfileDetailsUseCase
import com.kekulta.events.domain.usecase.EventDetailsUseCase
import com.kekulta.events.domain.usecase.EventRegistrationUseCase
import com.kekulta.events.domain.usecase.GroupDetailsUseCase
import com.kekulta.events.domain.usecase.LogOutUseCase
import com.kekulta.events.domain.usecase.MyPastEventsUseCase
import com.kekulta.events.domain.usecase.MyPlannedEventsUseCase
import com.kekulta.events.domain.usecase.RegisterUseCase
import com.kekulta.events.domain.usecase.SendCodeUseCase
import com.kekulta.events.presentation.viewmodel.CurrentProfileItemUseCase
import com.kekulta.events.presentation.viewmodel.EnterCodeScreenViewModel
import com.kekulta.events.presentation.viewmodel.EnterPhoneScreenViewModel
import com.kekulta.events.presentation.viewmodel.EnterProfileScreenViewModel
import com.kekulta.events.presentation.viewmodel.EventDetailsScreenViewModel
import com.kekulta.events.presentation.viewmodel.EventsScreenViewModel
import com.kekulta.events.presentation.viewmodel.GroupDetailsScreenViewModel
import com.kekulta.events.presentation.viewmodel.GroupsScreenViewModel
import com.kekulta.events.presentation.viewmodel.MoreScreenViewModel
import com.kekulta.events.presentation.viewmodel.MyEventsScreenViewModel
import com.kekulta.events.presentation.viewmodel.ProfileItemFormatter
import com.kekulta.events.presentation.viewmodel.ProfileScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val servicesModule = module {
    singleOf(::MockUsersService)
    singleOf(::MockAuthService)
}

val usecaseModule = module {
    factoryOf(::CurrentProfileDetailsUseCase)
    factoryOf(::LogOutUseCase)
    factoryOf(::EventDetailsUseCase)
    factoryOf(::CurrentAuthStatusUseCase)
    factoryOf(::SendCodeUseCase)
    factoryOf(::EventRegistrationUseCase)
    factoryOf(::CheckCodeUseCase)
    factoryOf(::AllEventsUseCase)
    factoryOf(::ActiveEventsUseCase)
    factoryOf(::CurrentProfileItemUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::AllGroupsUseCase)
    factoryOf(::GroupDetailsUseCase)
    factoryOf(::MyPastEventsUseCase)
    factoryOf(::MyPlannedEventsUseCase)
}

val formattersModule = module {
    factoryOf(::EventDetailsFormatter)
    factoryOf(::ProfileItemFormatter)
    factoryOf(::ActiveEventItemVoFormatter)
    factoryOf(::EventItemVoFormatter)
    factoryOf(::GroupItemVoFormatter)
    factoryOf(::ProfileDetailsFormatter)
}

val repositoriesModule = module {
    singleOf(::ProfileRepositoryMock) { bind<ProfileRepository>() }
    singleOf(::AuthRepositoryMock) { bind<AuthRepository>() }
    singleOf(::GroupsRepositoryMock) { bind<GroupsRepository>() }
    singleOf(::EventsRepositoryMock) { bind<EventsRepository>() }
    singleOf(::UsersRepositoryMock) { bind<UsersRepository>() }
}

val viewModelsModule = module {
    viewModelOf(::EventDetailsScreenViewModel)
    viewModelOf(::GroupsScreenViewModel)
    viewModelOf(::EventsScreenViewModel)
    viewModelOf(::MyEventsScreenViewModel)
    viewModelOf(::GroupDetailsScreenViewModel)
    viewModelOf(::ProfileScreenViewModel)
    viewModelOf(::EnterPhoneScreenViewModel)
    viewModelOf(::EnterProfileScreenViewModel)
    viewModelOf(::EnterCodeScreenViewModel)
    viewModelOf(::MoreScreenViewModel)
}


