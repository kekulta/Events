package com.kekulta.events.ui.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

class NavComponentNavigator(private val navController: NavController) : Navigator {
    override fun navTo(dest: Screen) {
        val screen = currScreen()

        /*
             There are some flickering if you navigate to the destination you are currently in.
             launchSingleTop doesn't help.
             So we just prevent navigation in the same root tab. Should help.
         */
        if (screen.tab == dest.tab && dest.isRoot && screen.isRoot) return

        navController.navigate(dest) {
            /*
                 Backstack is really messed up. We basically store every action user
                 ever did in here. Needs some fix.

                 Probably will cause some problems with nested navigation.
            */
            launchSingleTop = true
            restoreState = true
        }
    }

    override fun popBack() {
        navController.popBackStack()
    }

    override fun currTab(): Tab = currScreen().tab

    private fun currScreen(): Screen {

        return requireNotNull(navController.currentBackStackEntry?.toRoute<AnyScreen>())
    }

    /*
        For reasons I still can not understand `toRoute` throws when I try to use it with an interface.
        Yet it will work with any other destination even if they are mismatched.
        So I created this special destination which should not be navigated ever and serves
        only as model to retrieve data from unknown destination with its interface.
     */
    @Serializable
    private data class AnyScreen(
        override val tab: Tab = Tab.EVENTS,
        override val name: String = "Events",
        override val isRoot: Boolean = true,
    ) : Screen
}

