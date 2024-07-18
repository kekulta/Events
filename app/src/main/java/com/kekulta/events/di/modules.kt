package com.kekulta.events.di

import com.kekulta.events.domain.formatters.EventDetailsFormatter
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.domain.repository.api.UsersRepository
import com.kekulta.events.domain.repository.mock.EventsRepositoryMock
import com.kekulta.events.domain.repository.mock.ProfileRepositoryMock
import com.kekulta.events.domain.repository.mock.UsersRepositoryMock
import com.kekulta.events.domain.usecase.EventDetailsUseCase
import com.kekulta.events.domain.usecase.EventRegistrationUseCase
import com.kekulta.events.presentation.viewmodel.ActiveEventItemVoFormatter
import com.kekulta.events.presentation.viewmodel.ActiveEventsUseCase
import com.kekulta.events.presentation.viewmodel.AllEventsUseCase
import com.kekulta.events.presentation.viewmodel.EventDetailsViewModel
import com.kekulta.events.presentation.viewmodel.EventItemVoFormatter
import com.kekulta.events.presentation.viewmodel.EventsScreenViewModel
import com.kekulta.events.presentation.viewmodel.GroupDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val viewModelsModule = module {
    viewModelOf(::EventDetailsViewModel)
    viewModelOf(::GroupDetailsViewModel)
    viewModelOf(::EventsScreenViewModel)
    singleOf(::ProfileRepositoryMock) { bind<ProfileRepository>() }
    singleOf(::EventsRepositoryMock) { bind<EventsRepository>() }
    singleOf(::UsersRepositoryMock) { bind<UsersRepository>() }
    factoryOf(::EventDetailsUseCase)
    factoryOf(::EventDetailsFormatter)
    factoryOf(::EventRegistrationUseCase)
    factoryOf(::AllEventsUseCase)
    factoryOf(::ActiveEventsUseCase)
    factoryOf(::ActiveEventItemVoFormatter)
    factoryOf(::EventItemVoFormatter)
}
