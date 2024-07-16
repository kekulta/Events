package com.kekulta.events.presentation.ui.showcase

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kekulta.events.presentation.ui.widgets.base.snackbar.SnackbarScope

@Composable
fun ShowcaseScreen(snackbarScope: SnackbarScope) {

    val focusedSource = remember { MutableInteractionSource() }
    val hoveredSource = remember { MutableInteractionSource() }

    /*
        It was once changeable and maybe will bw again one day
     */
    var isEnabled by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        showcaseFirst(
            snackbarScope = snackbarScope,
            focusedSource = focusedSource,
            hoveredSource = hoveredSource,
            isEnabled = isEnabled
        )
        showcaseSecond(snackbarScope)
    }
}
