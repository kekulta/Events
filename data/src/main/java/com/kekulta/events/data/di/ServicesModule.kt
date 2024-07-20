package com.kekulta.events.data.di

import com.kekulta.events.data.mock.service.MockAuthService
import com.kekulta.events.data.mock.service.MockUsersService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val servicesModule = module {
    singleOf(::MockUsersService)
    singleOf(::MockAuthService)
}

