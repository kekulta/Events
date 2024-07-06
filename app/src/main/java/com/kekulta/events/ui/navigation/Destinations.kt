package com.kekulta.events.ui.navigation

import kotlinx.serialization.Serializable

enum class Tab {
    EVENTS, GROUPS, MORE, NO_TAB, NO_BAR, ANY,
}

@Serializable
data object Events : Screen {
    override val tab: Tab = Tab.EVENTS
    override val name: String = "Events"
    override val isRoot: Boolean = true
}

@Serializable
data object MyEvents : Screen {
    override val tab: Tab = Tab.MORE
    override val name: String = "My Events"
    override val isRoot: Boolean = false
}

@Serializable
data object Groups : Screen {
    override val tab: Tab = Tab.GROUPS
    override val name: String = "Groups"
    override val isRoot: Boolean = true
}

@Serializable
data object More : Screen {
    override val tab: Tab = Tab.MORE
    override val name: String = "More"
    override val isRoot: Boolean = true
}

@Serializable
data object Profile : Screen {
    override val tab: Tab = Tab.MORE
    override val name: String = "Profile"
    override val isRoot: Boolean = false
}

@Serializable
data object Showcase : Screen {
    override val tab: Tab = Tab.MORE
    override val name: String = "Showcase"
    override val isRoot: Boolean = false
}
