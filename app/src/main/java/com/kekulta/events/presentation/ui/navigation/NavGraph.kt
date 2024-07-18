package com.kekulta.events.presentation.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kekulta.events.presentation.ui.screens.login.EnterCodeScreen
import com.kekulta.events.presentation.ui.screens.login.EnterPhoneScreen
import com.kekulta.events.presentation.ui.screens.login.EnterProfileScreen
import com.kekulta.events.presentation.ui.screens.main.EventDetailsScreen
import com.kekulta.events.presentation.ui.screens.main.EventsScreen
import com.kekulta.events.presentation.ui.screens.main.GroupDetailsScreen
import com.kekulta.events.presentation.ui.screens.main.GroupsScreen
import com.kekulta.events.presentation.ui.screens.main.MoreScreen
import com.kekulta.events.presentation.ui.screens.main.MyEventsScreen
import com.kekulta.events.presentation.ui.screens.main.ProfileScreen
import com.kekulta.events.presentation.ui.screens.splash.SplashScreen
import com.kekulta.events.presentation.ui.widgets.base.snackbar.SnackbarScope

@Composable
fun EventsNavGraph(
    navController: NavHostController,
    snackbarScope: SnackbarScope,
    navState: MutableState<EventsNavBarState>,
) {
    NavHost(
        navController,
        startDestination = Splash(),
        enterTransition = {
            fadeIn(animationSpec = tween(200))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(200))
        },
    ) {
        screen<Events>(state = navState) {
            EventsScreen()
        }

        screen<Groups>(state = navState) {
            GroupsScreen()
        }

        screen<More>(state = navState) {
            MoreScreen()
        }

        screen<Profile>(state = navState) {
            ProfileScreen()
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

        screen<Splash>(state = navState) {
            SplashScreen()
        }

        screen<EnterPhone>(state = navState, slide = true) {
            EnterPhoneScreen()
        }

        screen<EnterCode>(state = navState, slide = true) { (_, dest) ->
            EnterCodeScreen(dest.phone)
        }

        screen<EnterProfile>(state = navState, slide = true) {
            EnterProfileScreen()
        }
    }
}
