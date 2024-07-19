package com.kekulta.events.domain.di

import org.koin.dsl.module

val domainModules = module { includes(usecasesModule) }
