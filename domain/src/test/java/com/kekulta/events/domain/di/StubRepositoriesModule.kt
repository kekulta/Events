package com.kekulta.events.domain.di

import com.kekulta.events.domain.stubs.repository.StubAuthRepository
import com.kekulta.events.domain.stubs.repository.StubCommunitiesRepository
import com.kekulta.events.domain.stubs.repository.StubEventsRepository
import com.kekulta.events.domain.stubs.repository.StubProfileRepository
import com.kekulta.events.domain.stubs.repository.StubUsersRepository
import com.kekulta.events.domain.repository.api.AuthRepository
import com.kekulta.events.domain.repository.api.CommunitiesRepository
import com.kekulta.events.domain.repository.api.EventsRepository
import com.kekulta.events.domain.repository.api.ProfileRepository
import com.kekulta.events.domain.repository.api.UsersRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val stubRepositoriesModule = module {
    factoryOf(::StubProfileRepository).bind<ProfileRepository>()
    factoryOf(::StubAuthRepository).bind<AuthRepository>()
    factoryOf(::StubUsersRepository).bind<UsersRepository>()
    factoryOf(::StubCommunitiesRepository).bind<CommunitiesRepository>()
    factoryOf(::StubEventsRepository).bind<EventsRepository>()
}
