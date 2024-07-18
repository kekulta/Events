package com.kekulta.events.di

import com.kekulta.events.data.MockAuthService
import com.kekulta.events.data.MockUsersService
import com.kekulta.events.domain.formatters.ActiveEventItemVoFormatter
import com.kekulta.events.domain.formatters.EventDetailsFormatter
import com.kekulta.events.domain.formatters.EventItemVoFormatter
import com.kekulta.events.domain.formatters.GroupItemVoFormatter
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
import com.kekulta.events.domain.usecase.EventDetailsUseCase
import com.kekulta.events.domain.usecase.EventRegistrationUseCase
import com.kekulta.events.domain.usecase.GroupDetailsUseCase
import com.kekulta.events.domain.usecase.MyPastEventsUseCase
import com.kekulta.events.domain.usecase.MyPlannedEventsUseCase
import com.kekulta.events.domain.usecase.CurrentAuthStatusUseCase
import com.kekulta.events.presentation.viewmodel.CurrentProfileUseCase
import com.kekulta.events.presentation.viewmodel.EnterPhoneScreenViewModel
import com.kekulta.events.presentation.viewmodel.EventDetailsViewModel
import com.kekulta.events.presentation.viewmodel.EventsScreenViewModel
import com.kekulta.events.presentation.viewmodel.GroupDetailsViewModel
import com.kekulta.events.presentation.viewmodel.GroupsScreenViewModel
import com.kekulta.events.presentation.viewmodel.LogOutUseCase
import com.kekulta.events.presentation.viewmodel.MyEventsScreenViewModel
import com.kekulta.events.presentation.viewmodel.ProfileFormatter
import com.kekulta.events.presentation.viewmodel.ProfileScreenViewModel
import com.kekulta.events.domain.usecase.SendCodeUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val viewModelsModule = module {
    viewModelOf(::EventDetailsViewModel)
    viewModelOf(::GroupsScreenViewModel)
    viewModelOf(::EventsScreenViewModel)
    viewModelOf(::MyEventsScreenViewModel)
    viewModelOf(::GroupDetailsViewModel)
    viewModelOf(::ProfileScreenViewModel)
    viewModelOf(::EnterPhoneScreenViewModel)
    singleOf(::MockUsersService)
    singleOf(::MockAuthService)
    singleOf(::ProfileRepositoryMock) { bind<ProfileRepository>() }
    singleOf(::AuthRepositoryMock) { bind<AuthRepository>() }
    singleOf(::GroupsRepositoryMock) { bind<GroupsRepository>() }
    singleOf(::EventsRepositoryMock) { bind<EventsRepository>() }
    singleOf(::UsersRepositoryMock) { bind<UsersRepository>() }
    factoryOf(::EventDetailsUseCase)
    factoryOf(::CurrentAuthStatusUseCase)
    factoryOf(::SendCodeUseCase)
    factoryOf(::EventDetailsFormatter)
    factoryOf(::EventRegistrationUseCase)
    factoryOf(::AllEventsUseCase)
    factoryOf(::ActiveEventsUseCase)
    factoryOf(::ActiveEventItemVoFormatter)
    factoryOf(::EventItemVoFormatter)
    factoryOf(::MyPastEventsUseCase)
    factoryOf(::MyPlannedEventsUseCase)
    factoryOf(::GroupItemVoFormatter)
    factoryOf(::AllGroupsUseCase)
    factoryOf(::GroupDetailsUseCase)
    factoryOf(::ProfileFormatter)
    factoryOf(::CurrentProfileUseCase)
    factoryOf(::LogOutUseCase)
}
