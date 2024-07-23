package com.kekulta.events.domain.di

import com.kekulta.events.domain.interactor.CancelEventRegistrationInteractor
import com.kekulta.events.domain.interactor.impl.CancelEventRegistrationInteractorImpl
import com.kekulta.events.domain.interactor.CheckCodeInteractor
import com.kekulta.events.domain.interactor.impl.CheckCodeInteractorImpl
import com.kekulta.events.domain.interactor.GetActiveEventsInteractor
import com.kekulta.events.domain.interactor.impl.GetActiveEventsInteractorImpl
import com.kekulta.events.domain.interactor.GetAllCommunitiesInteractor
import com.kekulta.events.domain.interactor.impl.GetAllCommunitiesInteractorImpl
import com.kekulta.events.domain.interactor.GetAllEventsInteractor
import com.kekulta.events.domain.interactor.impl.GetAllEventsInteractorImpl
import com.kekulta.events.domain.interactor.GetCommunityDetailsInteractor
import com.kekulta.events.domain.interactor.impl.GetCommunityDetailsInteractorImpl
import com.kekulta.events.domain.interactor.GetCurrentAuthStatusInteractor
import com.kekulta.events.domain.interactor.impl.GetCurrentAuthStatusInteractorImpl
import com.kekulta.events.domain.interactor.GetCurrentProfileInteractor
import com.kekulta.events.domain.interactor.impl.GetCurrentProfileInteractorImpl
import com.kekulta.events.domain.interactor.GetEventDetailsInteractor
import com.kekulta.events.domain.interactor.impl.GetEventDetailsInteractorImpl
import com.kekulta.events.domain.interactor.GetMyPastEventsInteractor
import com.kekulta.events.domain.interactor.impl.GetMyPastEventsInteractorImpl
import com.kekulta.events.domain.interactor.GetMyPlannedEventsInteractor
import com.kekulta.events.domain.interactor.impl.GetMyPlannedEventsInteractorImpl
import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.domain.interactor.impl.LogOutInteractorImpl
import com.kekulta.events.domain.interactor.RegisterInteractor
import com.kekulta.events.domain.interactor.impl.RegisterInteractorImpl
import com.kekulta.events.domain.interactor.RegisterToEventInteractor
import com.kekulta.events.domain.interactor.impl.RegisterToEventInteractorImpl
import com.kekulta.events.domain.interactor.SendCodeInteractor
import com.kekulta.events.domain.interactor.impl.SendCodeInteractorImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val interactorsModule = module {
    factoryOf(::CancelEventRegistrationInteractorImpl) bind CancelEventRegistrationInteractor::class
    factoryOf(::LogOutInteractorImpl) bind LogOutInteractor::class
    factoryOf(::SendCodeInteractorImpl) bind SendCodeInteractor::class
    factoryOf(::RegisterToEventInteractorImpl) bind RegisterToEventInteractor::class
    factoryOf(::CheckCodeInteractorImpl) bind CheckCodeInteractor::class
    factoryOf(::RegisterInteractorImpl) bind RegisterInteractor::class
    factoryOf(::GetEventDetailsInteractorImpl) bind GetEventDetailsInteractor::class
    factoryOf(::GetCurrentAuthStatusInteractorImpl) bind GetCurrentAuthStatusInteractor::class
    factoryOf(::GetAllEventsInteractorImpl) bind GetAllEventsInteractor::class
    factoryOf(::GetActiveEventsInteractorImpl) bind GetActiveEventsInteractor::class
    factoryOf(::GetCurrentProfileInteractorImpl) bind GetCurrentProfileInteractor::class
    factoryOf(::GetAllCommunitiesInteractorImpl) bind GetAllCommunitiesInteractor::class
    factoryOf(::GetCommunityDetailsInteractorImpl) bind GetCommunityDetailsInteractor::class
    factoryOf(::GetMyPastEventsInteractorImpl) bind GetMyPastEventsInteractor::class
    factoryOf(::GetMyPlannedEventsInteractorImpl) bind GetMyPlannedEventsInteractor::class
}
