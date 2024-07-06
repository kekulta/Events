package com.kekulta.events.ui.navigation

import kotlinx.serialization.Serializable

/*
    All destinations *must* be data classes and not data objects!
    Otherwise they will throw error we try to read them from backstack by interface.
 */

@Serializable
data class Events(
    override val tab: Tab = Tab.EVENTS,
    override val name: String = "Events",
    override val isRoot: Boolean = true,
) : Screen

@Serializable
data class MyEvents(
    override val tab: Tab = Tab.MORE,
    override val name: String = "My Events",
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class Groups(
    override val tab: Tab = Tab.GROUPS,
    override val name: String = "Groups",
    override val isRoot: Boolean = true,
) : Screen

@Serializable
data class More(
    override val tab: Tab = Tab.MORE,
    override val name: String = "More",
    override val isRoot: Boolean = true,
) : Screen

@Serializable
data class Profile(
    override val tab: Tab = Tab.MORE,
    override val name: String = "Profile",
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class Showcase(
    override val tab: Tab = Tab.MORE,
    override val name: String = "Showcase",
    override val isRoot: Boolean = false,
) : Screen

@Serializable
data class GroupDetails(
    val id: String,
    override val name: String,
    override val tab: Tab,
) : Screen {
    override val isRoot = false
}

@Serializable
data class EventDetails(
    val id: String,
    override val name: String,
    override val tab: Tab,
) : Screen {
    override val isRoot = false
}
