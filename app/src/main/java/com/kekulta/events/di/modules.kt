package com.kekulta.events.di

import com.kekulta.events.presentation.viewmodel.EventDetailViewModel
import com.kekulta.events.presentation.viewmodel.EventDetailsFormatter
import com.kekulta.events.presentation.viewmodel.EventDetailsUseCase
import com.kekulta.events.presentation.viewmodel.EventsRepository
import com.kekulta.events.presentation.viewmodel.EventsRepositoryMock
import com.kekulta.events.presentation.viewmodel.ProfileRepository
import com.kekulta.events.presentation.viewmodel.ProfileRepositoryMock
import com.kekulta.events.presentation.viewmodel.UsersRepository
import com.kekulta.events.presentation.viewmodel.UsersRepositoryMock
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val viewModelsModule = module {
    viewModelOf(::EventDetailViewModel)
    singleOf(::ProfileRepositoryMock) { bind<ProfileRepository>() }
    singleOf(::EventsRepositoryMock) { bind<EventsRepository>() }
    singleOf(::UsersRepositoryMock) { bind<UsersRepository>() }
    factoryOf(::EventDetailsUseCase)
    factoryOf(::EventDetailsFormatter)
}
