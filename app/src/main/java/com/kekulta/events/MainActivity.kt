package com.kekulta.events

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kekulta.events.ui.base.snackbar.rememberSnackbarScope
import com.kekulta.events.ui.elements.EventsNavBar
import com.kekulta.events.ui.elements.Tab
import com.kekulta.events.ui.showcase.Showcase
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
                var currTab by remember {
                    mutableStateOf(Tab.EVENTS)
                }

                Scaffold(
                    containerColor = EventsTheme.colors.neutralWhite,
                    bottomBar = {
                        EventsNavBar(currentTab = currTab, onClick = { tab ->
                            currTab = tab
                            val screen =
                                when (tab) {
                                    Tab.EVENTS -> Events
                                    Tab.GROUPS -> Groups
                                    Tab.MORE -> More
                                    else -> null
                                }

                            screen?.let {
                                navController.navigate(it) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
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
                        NavHost(navController, startDestination = Events) {
                            composable<Events> {
                                Showcase(snackbarScope)
                            }

                            composable<Groups> { backStackEntry ->
                                Text(text = "Groups")
                            }

                            composable<More> { backStackEntry ->
                                Text(text = "More")
                            }
                        }
                    }
                }
            }
        }
    }
}

interface Screen {
    val tab: Tab
}

@Serializable
data object Events : Screen {
    override val tab: Tab = Tab.GROUPS
}

@Serializable
data object Groups : Screen {
    override val tab: Tab = Tab.GROUPS
}

@Serializable
data object More : Screen {
    override val tab: Tab = Tab.GROUPS
}