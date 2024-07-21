package com.kekulta.events.data.di

import com.kekulta.events.data.mock.repository.AuthRepositoryMock
import com.kekulta.events.data.mock.repository.EventsRepositoryMock
import com.kekulta.events.data.mock.repository.CommunitiesRepositoryMock
import com.kekulta.events.data.mock.repository.ProfileRepositoryMock
import com.kekulta.events.data.mock.repository.UsersRepositoryMock
import com.kekulta.events.domain.repository.api.AuthRepository
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.CommunitiesRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.domain.repository.api.UsersRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val repositoriesModule = module {
    /*
        Stateless repos
     */
    factoryOf(::ProfileRepositoryMock) bind ProfileRepository::class
    factoryOf(::AuthRepositoryMock)  bind AuthRepository::class
    factoryOf(::UsersRepositoryMock) bind UsersRepository::class

    /*
        Stateful repos
     */
    singleOf(::CommunitiesRepositoryMock) bind CommunitiesRepository::class
    singleOf(::EventsRepositoryMock) bind EventsRepository::class
}
