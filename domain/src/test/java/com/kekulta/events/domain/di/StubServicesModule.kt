package com.kekulta.events.domain.di

import com.kekulta.events.domain.stubs.service.StubAuthService
import com.kekulta.events.domain.stubs.service.StubCommunityMembersService
import com.kekulta.events.domain.stubs.service.StubCommunityService
import com.kekulta.events.domain.stubs.service.StubEventsRegistrationService
import com.kekulta.events.domain.stubs.service.StubEventsService
import com.kekulta.events.domain.stubs.service.StubUsersService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val stubServicesModule = module {
    singleOf(::StubUsersService)
    singleOf(::StubAuthService)
    singleOf(::StubCommunityMembersService)
    singleOf(::StubCommunityService)
    singleOf(::StubEventsService)
    singleOf(::StubEventsRegistrationService)
}

