package com.kekulta.events.presentation.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.kekulta.events.presentation.ui.navigation.EventsNavGraph
import com.kekulta.events.presentation.ui.navigation.ProvideNavigator
import com.kekulta.events.presentation.ui.navigation.findNavigator
import com.kekulta.events.presentation.ui.navigation.rememberNavState
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.EventsNavBar
import com.kekulta.events.presentation.ui.widgets.EventsTopBar
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.ProvideEventsTopBarState
import com.kekulta.events.presentation.ui.widgets.base.modifiers.noIndicationClickable
import com.kekulta.events.presentation.ui.widgets.base.snackbar.ProvideSnackbarScope
import com.kekulta.events.presentation.ui.widgets.base.snackbar.rememberSnackbarScope
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )

        setContent {
            KoinAndroidContext {
                EventsTheme {
                    val navController = rememberNavController()
                    ProvideNavigator(navController = navController, onExit = { finish() }) {
                        val navigator = findNavigator()

                        val snackbarHostState = remember { SnackbarHostState() }
                        val snackbarScope =
                            rememberSnackbarScope(snackbarHostState = snackbarHostState)
                        val focusManager = LocalFocusManager.current
                        val navBarState = rememberNavState()

                        val topBarState = remember {
                            mutableStateOf(
                                EventsTopBarState(
                                    enabled = false,
                                    showBackButton = false,
                                    currScreenAction = null,
                                    currScreenName = "",
                                )
                            )
                        }

                        Scaffold(
                            containerColor = EventsTheme.colors.neutralWhite,
                            topBar = {
                                EventsTopBar(
                                    state = topBarState,
                                )
                            },
                            bottomBar = {
                                EventsNavBar(state = navBarState)
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
                                ProvideSnackbarScope(snackbarScope = snackbarScope) {
                                    ProvideEventsTopBarState(state = topBarState) {
                                        EventsNavGraph(
                                            navController = navController,
                                            snackbarScope = snackbarHostState,
                                            navState = navBarState,
                                        )
                                    }
                                }
                            }
                        }

                        BackHandler {
                            navigator.popBack()
                        }
                    }
                }
            }
        }
    }
}
