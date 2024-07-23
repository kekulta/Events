package com.kekulta.events.presentation.di


import com.kekulta.events.presentation.viewmodel.EnterCodeScreenViewModel
import com.kekulta.events.presentation.viewmodel.EnterPhoneScreenViewModel
import com.kekulta.events.presentation.viewmodel.EnterProfileScreenViewModel
import com.kekulta.events.presentation.viewmodel.EventDetailsScreenViewModel
import com.kekulta.events.presentation.viewmodel.EventsScreenViewModel
import com.kekulta.events.presentation.viewmodel.CommunityDetailsScreenViewModel
import com.kekulta.events.presentation.viewmodel.CommunitiesScreenViewModel
import com.kekulta.events.presentation.viewmodel.MoreScreenViewModel
import com.kekulta.events.presentation.viewmodel.MyEventsScreenViewModel
import com.kekulta.events.presentation.viewmodel.ProfileScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val viewModelsModule = module {
    viewModelOf(::EventDetailsScreenViewModel)
    viewModelOf(::CommunitiesScreenViewModel)
    viewModelOf(::EventsScreenViewModel)
    viewModelOf(::MyEventsScreenViewModel)
    viewModelOf(::CommunityDetailsScreenViewModel)
    viewModelOf(::ProfileScreenViewModel)
    viewModelOf(::EnterPhoneScreenViewModel)
    viewModelOf(::EnterProfileScreenViewModel)
    viewModelOf(::EnterCodeScreenViewModel)
    viewModelOf(::MoreScreenViewModel)
}
