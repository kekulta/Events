package com.kekulta.events.ui.navigation

import kotlinx.serialization.Serializable


/*
    Flows are top level destinations. The SplashFlow is an entry flow for the app.
    It them navigates to the MainFlow which contains AppBar, NavBar and can navigate between
    in-app destinations.
    This maybe not the best solution ever, but it is the best solution I can think of
    right now.
 */

interface AppFlow

@Serializable
data object SplashFlow : AppFlow

@Serializable
data object MainFlow : AppFlow
