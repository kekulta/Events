package com.kekulta.events

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.kekulta.events.ui.base.snackbar.rememberSnackbarScope
import com.kekulta.events.ui.elements.EventsNavBar
import com.kekulta.events.ui.elements.Tab
import com.kekulta.events.ui.showcase.ShowcaseFirst
import com.kekulta.events.ui.showcase.ShowcaseSecond
import com.kekulta.events.ui.theme.EventsTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )

        setContent {
            EventsTheme {

                val snackbarHostState = remember { SnackbarHostState() }
                val snackbarScope = rememberSnackbarScope(snackbarHostState = snackbarHostState)
                val focusManager = LocalFocusManager.current
                Scaffold(
                    containerColor = EventsTheme.colors.neutralWhite,
                    bottomBar = {
                        var currTab by remember {
                            mutableStateOf(Tab.NO_TAB)
                        }

                        EventsNavBar(currentTab = currTab, onClick = { tab -> currTab = tab })
                    },
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }, modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            }, indication = null
                        ) {
                            focusManager.clearFocus()
                        }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    ) {
                        ShowcaseFirst(snackbarScope)
                        ShowcaseSecond(snackbarScope)
                    }
                }
            }
        }
    }
}

// Define a home route that doesn't take any arguments
@Serializable
object Home

// Define a profile route that takes an ID
@Serializable
data class Profile(val id: String)