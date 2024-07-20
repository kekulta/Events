package com.kekulta.events.presentation.di


import com.kekulta.events.presentation.viewmodel.EnterCodeScreenViewModel
import com.kekulta.events.presentation.viewmodel.EnterPhoneScreenViewModel
import com.kekulta.events.presentation.viewmodel.EnterProfileScreenViewModel
import com.kekulta.events.presentation.viewmodel.EventDetailsScreenViewModel
import com.kekulta.events.presentation.viewmodel.EventsScreenViewModel
import com.kekulta.events.presentation.viewmodel.GroupDetailsScreenViewModel
import com.kekulta.events.presentation.viewmodel.GroupsScreenViewModel
import com.kekulta.events.presentation.viewmodel.MoreScreenViewModel
import com.kekulta.events.presentation.viewmodel.MyEventsScreenViewModel
import com.kekulta.events.presentation.viewmodel.ProfileScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val viewModelsModule = module {
    viewModelOf(::EventDetailsScreenViewModel)
    viewModelOf(::GroupsScreenViewModel)
    viewModelOf(::EventsScreenViewModel)
    viewModelOf(::MyEventsScreenViewModel)
    viewModelOf(::GroupDetailsScreenViewModel)
    viewModelOf(::ProfileScreenViewModel)
    viewModelOf(::EnterPhoneScreenViewModel)
    viewModelOf(::EnterProfileScreenViewModel)
    viewModelOf(::EnterCodeScreenViewModel)
    viewModelOf(::MoreScreenViewModel)
}
