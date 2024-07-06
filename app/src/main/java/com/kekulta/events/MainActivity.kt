package com.kekulta.events

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.rememberNavController
import com.kekulta.events.ui.widgets.base.modifiers.noIndicationClickable
import com.kekulta.events.ui.widgets.base.snackbar.rememberSnackbarScope
import com.kekulta.events.ui.widgets.EventsNavBar
import com.kekulta.events.ui.widgets.EventsTopBar
import com.kekulta.events.ui.navigation.Events
import com.kekulta.events.ui.navigation.EventsNavGraph
import com.kekulta.events.ui.navigation.NavigationState
import com.kekulta.events.ui.navigation.ProvideNavigator
import com.kekulta.events.ui.theme.EventsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )

        setContent {
            val navController = rememberNavController()

            ProvideNavigator(navController = navController) {
                EventsTheme {
                    val snackbarHostState = remember { SnackbarHostState() }
                    val snackbarScope = rememberSnackbarScope(snackbarHostState = snackbarHostState)
                    val focusManager = LocalFocusManager.current

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
                            EventsNavBar(currentTab = currScreenTab)
                        },
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .noIndicationClickable {
                                focusManager.clearFocus()
                            },
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            EventsNavGraph(
                                navController = navController,
                                snackbarScope = snackbarScope,
                                navState = navState
                            )
                        }
                    }
                }
            }
        }
    }
}
