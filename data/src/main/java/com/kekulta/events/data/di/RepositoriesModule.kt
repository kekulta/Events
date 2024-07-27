package com.kekulta.events.data.di

import com.kekulta.events.data.stubs.repository.StubAuthRepository
import com.kekulta.events.data.stubs.repository.StubCommunitiesRepository
import com.kekulta.events.data.stubs.repository.StubEventsRepository
import com.kekulta.events.data.stubs.repository.StubProfileRepository
import com.kekulta.events.data.stubs.repository.StubUsersRepository
import com.kekulta.events.domain.repository.api.AuthRepository
import com.kekulta.events.domain.repository.api.CommunitiesRepository
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.domain.repository.api.UsersRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val repositoriesModule = module {
    factoryOf(::StubProfileRepository).bind<ProfileRepository>()
    factoryOf(::StubAuthRepository).bind<AuthRepository>()
    factoryOf(::StubUsersRepository).bind<UsersRepository>()
    factoryOf(::StubCommunitiesRepository).bind<CommunitiesRepository>()
    factoryOf(::StubEventsRepository).bind<EventsRepository>()
}
