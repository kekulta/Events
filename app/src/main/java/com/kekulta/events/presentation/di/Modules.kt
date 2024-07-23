package com.kekulta.events.presentation.di

import com.kekulta.events.data.di.dataModules
import com.kekulta.events.domain.di.domainModules
import org.koin.dsl.module

val appModules = module { includes(formattersModule, viewModelsModule) }

val eventsAppModule = module { includes(appModules, domainModules, dataModules) }