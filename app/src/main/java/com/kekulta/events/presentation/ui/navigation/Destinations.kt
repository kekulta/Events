package com.kekulta.events.presentation.ui.navigation

import com.kekulta.events.domain.models.id.EventId
import com.kekulta.events.domain.models.id.CommunityId
import kotlinx.serialization.Serializable

/*
    All destinations *must* be data classes and not data objects!
    Otherwise they will throw error we try to read them from backstack by interface.
 */

@Serializable
data class Splash(
    override val tab: Tab = Tab.NO_BAR,
    /*
        There is no root in the NO_BAR tab.
     */
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class EnterProfile(
    override val tab: Tab = Tab.NO_BAR,
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class EnterPhone(
    override val tab: Tab = Tab.NO_BAR,
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class EnterCode(
    override val tab: Tab = Tab.NO_BAR,
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class Events(
    override val tab: Tab = Tab.EVENTS,
    override val isRoot: Boolean = true,
) : Screen

@Serializable
data class Communities(
    override val tab: Tab = Tab.COMMUNITIES,
    override val isRoot: Boolean = true,
) : Screen

@Serializable
data class More(
    override val tab: Tab = Tab.MORE,
    override val isRoot: Boolean = true,
) : Screen

@Serializable
data class MyEvents(
    override val tab: Tab = Tab.MORE,
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class Profile(
    override val tab: Tab = Tab.MORE,
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class CommunityDetails(
    val id: CommunityId,
    override val tab: Tab,
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class EventDetails(
    val id: EventId,
    override val tab: Tab,
    override val isRoot: Boolean = false,
) : Screen

