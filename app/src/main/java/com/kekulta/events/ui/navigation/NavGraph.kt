package com.kekulta.events.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kekulta.events.ui.screens.main.EventDetailsScreen
import com.kekulta.events.ui.screens.main.EventsAction
import com.kekulta.events.ui.screens.main.EventsScreen
import com.kekulta.events.ui.screens.main.GroupDetailsScreen
import com.kekulta.events.ui.screens.main.GroupsScreen
import com.kekulta.events.ui.screens.main.MoreScreen
import com.kekulta.events.ui.screens.main.MyEventsScreen
import com.kekulta.events.ui.screens.main.ProfileAction
import com.kekulta.events.ui.screens.main.ProfileScreen
import com.kekulta.events.ui.showcase.ShowcaseScreen
import com.kekulta.events.ui.widgets.base.snackbar.SnackbarScope
import com.kekulta.events.ui.widgets.base.snackbar.showSnackbar

@Composable
fun EventsNavGraph(
    navController: NavHostController,
    snackbarScope: SnackbarScope,
    navState: MutableState<NavigationState>,
    navToLogin: () -> Unit,
) {
    NavHost(
        navController,
        startDestination = Events(),
        enterTransition = {
            fadeIn(animationSpec = tween(200))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(200))
        },
    ) {
        screen<Events>(state = navState, screenAction = {
            EventsAction {
                snackbarScope.showSnackbar("Events action!")
            }
        }) {
            EventsScreen()
        }

        screen<Groups>(state = navState) {
            GroupsScreen()
        }

        screen<More>(state = navState) {
            MoreScreen(navToLogin)
        }

        screen<Profile>(state = navState, screenAction = {
            ProfileAction {
                snackbarScope.showSnackbar("Profile action!")
            }
        }) {
            ProfileScreen()
        }

        screen<Showcase>(state = navState) {
            ShowcaseScreen(snackbarScope)
        }
        screen<MyEvents>(state = navState) {
            MyEventsScreen()
        }

        screen<GroupDetails>(state = navState) { (_, dest) ->
            GroupDetailsScreen(id = dest.id)
        }

        screen<EventDetails>(state = navState) { (_, dest) ->
            EventDetailsScreen(id = dest.id)
        }
    }
}
