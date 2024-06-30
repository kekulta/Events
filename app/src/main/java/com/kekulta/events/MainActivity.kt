package com.kekulta.events

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kekulta.events.ui.base.buttons.debouncedClickable
import com.kekulta.events.ui.base.snackbar.rememberSnackbarScope
import com.kekulta.events.ui.base.snackbar.showSnackbar
import com.kekulta.events.ui.elements.EventsNavBar
import com.kekulta.events.ui.elements.EventsScreen
import com.kekulta.events.ui.elements.MoreScreen
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

                /*
                    Maybe we can win a few frames if we do something with immutability and
                    rememberUpdatedState here, not sure
                */
                var currScreen: Screen by remember {
                    mutableStateOf(Events)
                }
                var currAction: (@Composable () -> Unit)? by remember {
                    mutableStateOf(null)
                }

                fun navigate(dest: Screen) {
                    navController.navigate(dest) {
                        /*
                            Backstack is really messed up. We basically store every action user
                            ever did in here. Needs some fix.
                        */
                        launchSingleTop = true
                        restoreState = true
                    }
                }

                /*
                    Paddings are mess. Will fix that later.
                 */
                Scaffold(
                    containerColor = EventsTheme.colors.neutralWhite,
                    topBar = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .statusBarsPadding()
                                .padding(vertical = EventsTheme.sizes.sizeX6)
                        ) {
                            AnimatedVisibility(
                                visible = !currScreen.isRoot,
                                enter = fadeIn() + expandHorizontally(),
                                exit = shrinkHorizontally() + fadeOut(),
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(start = EventsTheme.sizes.sizeX8)
                                        .size(EventsTheme.sizes.sizeX12)
                                        .debouncedClickable(
                                            interactionSource = remember {
                                                MutableInteractionSource()
                                            },
                                            indication = null,
                                            onClick = { navController.popBackStack() }),
                                    painter = painterResource(id = R.drawable.icon_arr_left),
                                    tint = EventsTheme.colors.neutralActive,
                                    contentDescription = null
                                )
                            }

                            val namePadding by
                            animateDpAsState(
                                targetValue = if (currScreen.isRoot) EventsTheme.sizes.sizeX12 else EventsTheme.sizes.sizeX4,
                                label = "Name padding"
                            )

                            Text(
                                modifier = Modifier
                                    .padding(start = namePadding)
                                    .weight(1f),
                                text = currScreen.name,
                                style = EventsTheme.typography.subheading1
                            )

                            AnimatedContent(targetState = currAction, transitionSpec = {
                                slideInHorizontally { width -> width } + fadeIn() togetherWith slideOutHorizontally { width -> width } + fadeOut()
                            }, label = "Top Bar Action") { newAction ->
                                if (newAction != null) {
                                    Box(modifier = Modifier.padding(end = EventsTheme.sizes.sizeX12)) {
                                        newAction.invoke()
                                    }
                                }
                            }
                        }
                    },
                    bottomBar = {
                        EventsNavBar(currentTab = currScreen.tab, onClick = { tab ->
                            val screen = when (tab) {
                                Tab.EVENTS -> Events
                                Tab.GROUPS -> Groups
                                Tab.MORE -> More
                                else -> null
                            }

                            screen?.let {
                                navigate(it)
                            }

                        })
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
                        NavHost(navController, startDestination = Events,
                            enterTransition = { EnterTransition.None },
                            exitTransition = { ExitTransition.None }) {
                            composable<Events> { backStackEntry ->
                                currScreen = backStackEntry.toRoute<Events>()
                                currAction = {
                                    EventsAction {
                                        snackbarScope.showSnackbar("Events action!")
                                    }
                                }
                                EventsScreen()
                            }

                            composable<Groups> { backStackEntry ->
                                currScreen = backStackEntry.toRoute<Groups>()
                                currAction = null
                                Text(text = "Groups")
                            }

                            composable<More> { backStackEntry ->
                                currScreen = backStackEntry.toRoute<More>()
                                currAction = null
                                MoreScreen(::navigate)
                            }
                            composable<Profile> { backStackEntry ->
                                currScreen = backStackEntry.toRoute<Profile>()
                                currAction = {
                                    ProfileAction {
                                        snackbarScope.showSnackbar("Profile action!")
                                    }
                                }
                                ProfileScreen()
                            }

                            composable<Showcase> { backStackEntry ->
                                currScreen = backStackEntry.toRoute<Showcase>()
                                currAction = null
                                ShowcaseScreen(snackbarScope)
                            }
                        }
                    }
                }
            }
        }
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
                },
                indication = null,
                onClick = onClick
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
                },
                indication = null,
                onClick = onClick
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
