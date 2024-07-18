package com.kekulta.events.presentation.ui.navigation

import com.kekulta.events.domain.models.EventId
import com.kekulta.events.domain.models.GroupId
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
    val phone: String,
    override val tab: Tab = Tab.NO_BAR,
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class Events(
    override val tab: Tab = Tab.EVENTS,
    override val isRoot: Boolean = true,
) : Screen

@Serializable
data class Groups(
    override val tab: Tab = Tab.GROUPS,
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
data class GroupDetails(
    val id: GroupId,
    override val tab: Tab,
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class EventDetails(
    val id: EventId,
    override val tab: Tab,
    override val isRoot: Boolean = false,
) : Screen

