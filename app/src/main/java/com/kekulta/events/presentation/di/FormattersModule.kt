package com.kekulta.events.presentation.di

import com.kekulta.events.presentation.formatters.ActiveEventItemFormatter
import com.kekulta.events.presentation.formatters.EventDetailsFormatter
import com.kekulta.events.presentation.formatters.EventItemFormatter
import com.kekulta.events.presentation.formatters.GroupDetailsFormatter
import com.kekulta.events.presentation.formatters.GroupItemFormatter
import com.kekulta.events.presentation.formatters.ProfileDetailsFormatter
import com.kekulta.events.presentation.formatters.ProfileItemFormatter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val formattersModule = module {
    factoryOf(::GroupDetailsFormatter)
    factoryOf(::EventDetailsFormatter)
    factoryOf(::ProfileItemFormatter)
    factoryOf(::ActiveEventItemFormatter)
    factoryOf(::EventItemFormatter)
    factoryOf(::GroupItemFormatter)
    factoryOf(::ProfileDetailsFormatter)
}



