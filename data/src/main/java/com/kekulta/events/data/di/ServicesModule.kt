package com.kekulta.events.data.di

import com.kekulta.events.data.stubs.service.StubAuthService
import com.kekulta.events.data.stubs.service.StubCommunityMembersService
import com.kekulta.events.data.stubs.service.StubCommunityService
import com.kekulta.events.data.stubs.service.StubEventsRegistrationService
import com.kekulta.events.data.stubs.service.StubEventsService
import com.kekulta.events.data.stubs.service.StubUsersService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val servicesModule = module {
    singleOf(::StubUsersService)
    singleOf(::StubAuthService)
    singleOf(::StubCommunityMembersService)
    singleOf(::StubCommunityService)
    singleOf(::StubEventsService)
    singleOf(::StubEventsRegistrationService)
}

