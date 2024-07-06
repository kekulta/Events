package com.kekulta.events.ui.navigation

import androidx.navigation.NavController

class NavComponentNavigator(private val navController: NavController) : Navigator {
    override fun navTo(dest: Screen) {
        navController.navigate(dest) {
            /*
                 Backstack is really messed up. We basically store every action user
                 ever did in here. Needs some fix.

                 There are some flickering if you navigate to the destination you are
                 currently in. launchSingleTop does not help.
            */
            launchSingleTop = true
            restoreState = true
        }
    }

    override fun popBack() {
        navController.popBackStack()
    }
}

