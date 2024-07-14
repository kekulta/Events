package com.kekulta.events.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


class NavComponentNavigator(private val navController: NavController, val onExit: () -> Unit) : Navigator {
    override fun setRoot(dest: Screen) {
        navToInternal(dest, true)
    }

    override fun navTo(dest: Screen) {
        navToInternal(dest, false)
    }

    private fun navToInternal(dest: Screen, setRoot: Boolean) {
        val screen = currScreen()

        /*
             There are some flickering if you navigate to the destination you are currently in.
             launchSingleTop doesn't help.
             So we just prevent navigation in the same root tab. Should help.
         */
        if (screen.tab == dest.tab && dest.isRoot && screen.isRoot) return

        navController.navigate(dest) {
            when {
                setRoot -> {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    navController.graph.setStartDestination(dest)
                }

                dest.isRoot -> {
                    popUpTo(navController.graph.findStartDestination().id)
                }
            }
            launchSingleTop = true
        }
    }

    override fun popBack() {
        if(!navController.navigateUp()) {
            onExit()
        }
    }

    override fun currTab(): Tab = currScreen().tab

    private fun currScreen(): Screen {
        return requireNotNull(navController.currentBackStackEntry?.toRoute<AnyScreen>()) { "Empty backstack!" }
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
        override val isRoot: Boolean = true,
    ) : Screen
}
