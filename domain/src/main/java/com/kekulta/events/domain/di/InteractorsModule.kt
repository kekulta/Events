package com.kekulta.events.domain.di

import com.kekulta.events.domain.interactor.ActiveEventsInteractor
import com.kekulta.events.domain.interactor.AllEventsInteractor
import com.kekulta.events.domain.interactor.AllCommunitiesInteractor
import com.kekulta.events.domain.interactor.CancelEventRegistrationInteractor
import com.kekulta.events.domain.interactor.CheckCodeInteractor
import com.kekulta.events.domain.interactor.CurrentAuthStatusInteractor
import com.kekulta.events.domain.interactor.CurrentProfileInteractor
import com.kekulta.events.domain.interactor.EventDetailsInteractor
import com.kekulta.events.domain.interactor.CommunityDetailsInteractor
import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.domain.interactor.MyPastEventsInteractor
import com.kekulta.events.domain.interactor.MyPlannedEventsInteractor
import com.kekulta.events.domain.interactor.RegisterToEventInteractor
import com.kekulta.events.domain.interactor.RegisterInteractor
import com.kekulta.events.domain.interactor.SendCodeInteractor
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val interactorsModule = module {
    factoryOf(::CurrentProfileInteractor)
    factoryOf(::CancelEventRegistrationInteractor)
    factoryOf(::LogOutInteractor)
    factoryOf(::EventDetailsInteractor)
    factoryOf(::CurrentAuthStatusInteractor)
    factoryOf(::SendCodeInteractor)
    factoryOf(::RegisterToEventInteractor)
    factoryOf(::CheckCodeInteractor)
    factoryOf(::AllEventsInteractor)
    factoryOf(::ActiveEventsInteractor)
    factoryOf(::RegisterInteractor)
    factoryOf(::AllCommunitiesInteractor)
    factoryOf(::CommunityDetailsInteractor)
    factoryOf(::MyPastEventsInteractor)
    factoryOf(::MyPlannedEventsInteractor)
}
