package com.kekulta.events.domain.di

import GetMyPastEventsInteractorImpl
import com.kekulta.events.domain.interactor.CancelEventRegistrationInteractor
import com.kekulta.events.domain.interactor.CheckCodeInteractor
import com.kekulta.events.domain.interactor.GetActiveEventsInteractor
import com.kekulta.events.domain.interactor.GetAllCommunitiesInteractor
import com.kekulta.events.domain.interactor.GetAllEventsInteractor
import com.kekulta.events.domain.interactor.GetCommunityEventsInteractor
import com.kekulta.events.domain.interactor.GetCommunityInteractor
import com.kekulta.events.domain.interactor.GetCommunityMembersInteractor
import com.kekulta.events.domain.interactor.GetCurrentAuthStatusInteractor
import com.kekulta.events.domain.interactor.GetCurrentProfileInteractor
import com.kekulta.events.domain.interactor.GetEventInteractor
import com.kekulta.events.domain.interactor.GetEventVisitorsInteractor
import com.kekulta.events.domain.interactor.GetMyPastEventsInteractor
import com.kekulta.events.domain.interactor.GetMyPlannedEventsInteractor
import com.kekulta.events.domain.interactor.IsRegisteredToEventInteractor
import com.kekulta.events.domain.interactor.LogOutInteractor
import com.kekulta.events.domain.interactor.RegisterInteractor
import com.kekulta.events.domain.interactor.RegisterToEventInteractor
import com.kekulta.events.domain.interactor.SendCodeInteractor
import com.kekulta.events.domain.interactor.impl.CancelEventRegistrationInteractorImpl
import com.kekulta.events.domain.interactor.impl.CheckCodeInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetActiveEventsInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetAllCommunitiesInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetAllEventsInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetCommunityEventsInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetCommunityInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetCommunityMembersInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetCurrentAuthStatusInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetCurrentProfileInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetEventInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetEventVisitorsInteractorImpl
import com.kekulta.events.domain.interactor.impl.GetMyPlannedEventsInteractorImpl
import com.kekulta.events.domain.interactor.impl.IsRegisteredToEventInteractorImpl
import com.kekulta.events.domain.interactor.impl.LogOutInteractorImpl
import com.kekulta.events.domain.interactor.impl.RegisterInteractorImpl
import com.kekulta.events.domain.interactor.impl.RegisterToEventInteractorImpl
import com.kekulta.events.domain.interactor.impl.SendCodeInteractorImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val interactorsModule = module {
    factoryOf(::CancelEventRegistrationInteractorImpl).bind<CancelEventRegistrationInteractor>()
    factoryOf(::LogOutInteractorImpl).bind<LogOutInteractor>()
    factoryOf(::SendCodeInteractorImpl).bind<SendCodeInteractor>()
    factoryOf(::RegisterToEventInteractorImpl).bind<RegisterToEventInteractor>()
    factoryOf(::CheckCodeInteractorImpl).bind<CheckCodeInteractor>()
    factoryOf(::RegisterInteractorImpl).bind<RegisterInteractor>()
    factoryOf(::GetEventInteractorImpl).bind<GetEventInteractor>()
    factoryOf(::GetCurrentAuthStatusInteractorImpl).bind<GetCurrentAuthStatusInteractor>()
    factoryOf(::GetAllEventsInteractorImpl).bind<GetAllEventsInteractor>()
    factoryOf(::GetActiveEventsInteractorImpl).bind<GetActiveEventsInteractor>()
    factoryOf(::GetCurrentProfileInteractorImpl).bind<GetCurrentProfileInteractor>()
    factoryOf(::GetAllCommunitiesInteractorImpl).bind<GetAllCommunitiesInteractor>()
    factoryOf(::GetCommunityInteractorImpl).bind<GetCommunityInteractor>()
    factoryOf(::GetMyPastEventsInteractorImpl).bind<GetMyPastEventsInteractor>()
    factoryOf(::GetMyPlannedEventsInteractorImpl).bind<GetMyPlannedEventsInteractor>()
    factoryOf(::GetCommunityEventsInteractorImpl).bind<GetCommunityEventsInteractor>()
    factoryOf(::GetEventVisitorsInteractorImpl).bind<GetEventVisitorsInteractor>()
    factoryOf(::GetEventVisitorsInteractorImpl).bind<GetEventVisitorsInteractor>()
    factoryOf(::GetCommunityMembersInteractorImpl).bind<GetCommunityMembersInteractor>()
    factoryOf(::IsRegisteredToEventInteractorImpl).bind<IsRegisteredToEventInteractor>()
}
