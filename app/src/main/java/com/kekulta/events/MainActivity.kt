package com.kekulta.events

import android.graphics.BlurMaskFilter
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kekulta.events.ui.elements.EventsNavBar
import com.kekulta.events.ui.showcase.ShowcaseFirst
import com.kekulta.events.ui.showcase.ShowcaseSecond
import com.kekulta.events.ui.theme.EventsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventsTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                fun showSnackbar(text: String) {
                    scope.launch {
                        snackbarHostState.showSnackbar(text)
                    }
                }

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
                        ShowcaseFirst(snackbarHostState = snackbarHostState)
                        ShowcaseSecond(snackbarHostState = snackbarHostState)
                    }
                }
            }
        }
    }
}

