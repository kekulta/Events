package com.kekulta.events.data.di

import org.koin.dsl.module

val dataModules = module { includes(repositoriesModule, servicesModule) }