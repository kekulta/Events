package com.kekulta.events

import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.kekulta.events.ui.base.snackbar.rememberSnackbarScope
import com.kekulta.events.ui.elements.EventsNavBar
import com.kekulta.events.ui.showcase.ShowcaseFirst
import com.kekulta.events.ui.showcase.ShowcaseSecond
import com.kekulta.events.ui.theme.EventsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventsTheme {

                val snackbarHostState = remember { SnackbarHostState() }
                val snackbarScope = rememberSnackbarScope(snackbarHostState = snackbarHostState)

                val focusManager = LocalFocusManager.current
                Scaffold(
                    containerColor = EventsTheme.colors.neutralWhite,
                    bottomBar = {
                        EventsNavBar()
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
                        Text(
                            text = "text",
                        )
                        ShowcaseFirst(snackbarScope)
                        ShowcaseSecond(snackbarScope)
                    }
                }
            }
        }
    }
}

