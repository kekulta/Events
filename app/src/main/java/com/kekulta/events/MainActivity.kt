package com.kekulta.events

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kekulta.events.ui.showcase.Showcase
import com.kekulta.events.ui.showcase.TempSpacer
import com.kekulta.events.ui.theme.EventsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventsTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val focusManager = LocalFocusManager.current

                Scaffold(snackbarHost = {
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

                    Showcase(innerPadding = innerPadding, snackbarHostState = snackbarHostState)
                }
            }
        }
    }
}

