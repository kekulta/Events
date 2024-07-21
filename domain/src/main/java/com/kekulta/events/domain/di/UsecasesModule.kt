package com.kekulta.events.domain.di

import com.kekulta.events.domain.usecase.ActiveEventsUseCase
import com.kekulta.events.domain.usecase.AllEventsUseCase
import com.kekulta.events.domain.usecase.AllCommunitiesUseCase
import com.kekulta.events.domain.usecase.CancelEventRegistrationUseCase
import com.kekulta.events.domain.usecase.CheckCodeUseCase
import com.kekulta.events.domain.usecase.CurrentAuthStatusUseCase
import com.kekulta.events.domain.usecase.CurrentProfileUseCase
import com.kekulta.events.domain.usecase.EventDetailsUseCase
import com.kekulta.events.domain.usecase.CommunityDetailsUseCase
import com.kekulta.events.domain.usecase.LogOutUseCase
import com.kekulta.events.domain.usecase.MyPastEventsUseCase
import com.kekulta.events.domain.usecase.MyPlannedEventsUseCase
import com.kekulta.events.domain.usecase.RegisterToEventUseCase
import com.kekulta.events.domain.usecase.RegisterUseCase
import com.kekulta.events.domain.usecase.SendCodeUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val usecasesModule = module {
    factoryOf(::CurrentProfileUseCase)
    factoryOf(::CancelEventRegistrationUseCase)
    factoryOf(::LogOutUseCase)
    factoryOf(::EventDetailsUseCase)
    factoryOf(::CurrentAuthStatusUseCase)
    factoryOf(::SendCodeUseCase)
    factoryOf(::RegisterToEventUseCase)
    factoryOf(::CheckCodeUseCase)
    factoryOf(::AllEventsUseCase)
    factoryOf(::ActiveEventsUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::AllCommunitiesUseCase)
    factoryOf(::CommunityDetailsUseCase)
    factoryOf(::MyPastEventsUseCase)
    factoryOf(::MyPlannedEventsUseCase)
}
