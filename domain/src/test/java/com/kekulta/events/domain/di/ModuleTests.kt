package com.kekulta.events.domain.di

import org.koin.dsl.module

val stubModules = module { includes(stubRepositoriesModule, stubServicesModule) }