package com.kekulta.events

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kekulta.events.ui.base.buttons.debouncedClickable
import com.kekulta.events.ui.base.snackbar.rememberSnackbarScope
import com.kekulta.events.ui.base.snackbar.showSnackbar
import com.kekulta.events.ui.elements.EventsNavBar
import com.kekulta.events.ui.elements.EventsScreen
import com.kekulta.events.ui.elements.EventsTopBar
import com.kekulta.events.ui.elements.MoreScreen
import com.kekulta.events.ui.elements.MyEventsScreen
import com.kekulta.events.ui.elements.ProfileScreen
import com.kekulta.events.ui.elements.Tab
import com.kekulta.events.ui.showcase.ShowcaseScreen
import com.kekulta.events.ui.theme.EventsTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )

        setContent {
            EventsTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val snackbarScope = rememberSnackbarScope(snackbarHostState = snackbarHostState)
                val focusManager = LocalFocusManager.current
                val navController = rememberNavController()

                val navState = remember {
                    mutableStateOf(
                        NavigationState(
                            screen = Events,
                            action = null
                        )
                    )
                }

                val isRootScreen by remember { derivedStateOf { navState.value.screen.isRoot } }
                val currScreenTab by remember { derivedStateOf { navState.value.screen.tab } }
                val currScreenName by remember { derivedStateOf { navState.value.screen.name } }
                val currScreenAction by remember { derivedStateOf { navState.value.action } }

                fun navigate(dest: Screen) {
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

                Scaffold(
                    containerColor = EventsTheme.colors.neutralWhite,
                    topBar = {
                        EventsTopBar(
                            showBackButton = !isRootScreen,
                            onBackPressed = { navController.popBackStack() },
                            currScreenName = currScreenName,
                            currScreenAction = currScreenAction,
                        )
                    },
                    bottomBar = {
                        EventsNavBar(currentTab = currScreenTab, navigate = ::navigate)
                    },
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            }, indication = null
                        ) {
                            focusManager.clearFocus()
                        },
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController,
                            startDestination = Events,
                            enterTransition = { EnterTransition.None },
                            exitTransition = { ExitTransition.None }) {

                            screen<Events>(state = navState, screenAction = {
                                EventsAction {
                                    snackbarScope.showSnackbar("Events action!")
                                }
                            }) {
                                EventsScreen()
                            }

                            screen<Groups>(state = navState) {
                                Text(text = "Groups")
                            }

                            screen<More>(state = navState) {
                                MoreScreen(::navigate)
                            }

                            screen<Profile>(state = navState,
                                screenAction = {
                                    ProfileAction {
                                        snackbarScope.showSnackbar("Profile action!")
                                    }
                                }
                            ) {
                                ProfileScreen()
                            }

                            screen<Showcase>(state = navState) {
                                ShowcaseScreen(snackbarScope)
                            }
                            screen<MyEvents>(state = navState) {
                                MyEventsScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

inline fun <reified T : Screen> NavGraphBuilder.screen(
    state: MutableState<NavigationState>,
    noinline screenAction: @Composable (() -> Unit)? = null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T> { navBackStackEntry ->
        state.value = state.value.copy(
            screen = navBackStackEntry.toRoute<T>(),
            action = screenAction,
        )
        content(navBackStackEntry)
    }
}


@Composable
fun ProfileAction(onClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .size(EventsTheme.sizes.sizeX12)
            .debouncedClickable(
                interactionSource = remember {
                    MutableInteractionSource()
                }, indication = null, onClick = onClick
            ),
        painter = painterResource(id = R.drawable.icon_pencil),
        tint = EventsTheme.colors.neutralActive,
        contentDescription = null
    )
}

@Composable
fun EventsAction(onClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .size(EventsTheme.sizes.sizeX12)
            .debouncedClickable(
                interactionSource = remember {
                    MutableInteractionSource()
                }, indication = null, onClick = onClick
            ),
        painter = painterResource(id = R.drawable.icon_plus),
        tint = EventsTheme.colors.neutralActive,
        contentDescription = null
    )
}

interface Screen {
    val tab: Tab

    /*
        Unlocalizable. I should to something with it, but passing res ids around doesn't look good
        either.
    */
    val name: String
    val isRoot: Boolean
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

data class NavigationState(
    val screen: Screen,
    val action: @Composable (() -> Unit)?,
)
