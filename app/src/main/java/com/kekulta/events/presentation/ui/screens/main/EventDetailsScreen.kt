package com.kekulta.events.presentation.ui.screens.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.kekulta.events.R
import com.kekulta.events.domain.models.EventId
import com.kekulta.events.presentation.ui.models.EventDetailsVo
import com.kekulta.events.presentation.ui.models.ScreenState
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.AttendeesRow
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsOutlinedButton
import com.kekulta.events.presentation.ui.widgets.base.buttons.debouncedClickable
import com.kekulta.events.presentation.ui.widgets.base.chips.RoundChip
import com.kekulta.events.presentation.ui.widgets.base.modifiers.blur
import com.kekulta.events.presentation.ui.widgets.base.modifiers.noIndicationClickable
import com.kekulta.events.presentation.ui.widgets.base.snackbar.findSnackbarScope
import com.kekulta.events.presentation.ui.widgets.base.snackbar.showSnackbar
import com.kekulta.events.presentation.viewmodel.EventDetailsViewModel
import kotlinx.datetime.Clock
import me.saket.telephoto.zoomable.rememberZoomableState
import me.saket.telephoto.zoomable.zoomable
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventDetailsScreen(id: EventId, viewModel: EventDetailsViewModel = koinViewModel()) {
    viewModel.setId(id)
    val state by viewModel.observeState().collectAsStateWithLifecycle()

    /*
        Here we need intermediate variable or smartcast would be impossible otherwise.
     */
    when (val s = state) {
        is ScreenState.Loading -> {
            SetTopBar {
                remember {
                    EventsTopBarState(
                        enabled = true,
                        showBackButton = true,
                        currScreenAction = null,
                        currScreenName = "Loading.."
                    )
                }
            }
        }

        is ScreenState.Error -> {
            SetTopBar {
                remember {
                    EventsTopBarState(
                        enabled = true,
                        showBackButton = true,
                        currScreenAction = null,
                        currScreenName = "Error.."
                    )
                }
            }
        }

        is ScreenState.Success -> {
            SuccessScreen(vo = s.state, viewModel)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SuccessScreen(vo: EventDetailsVo, viewModel: EventDetailsViewModel) {
    val snackbarScope = findSnackbarScope()
    var isSelected by rememberSaveable {
        mutableStateOf(false)
    }

    BackHandler(enabled = isSelected) {
        isSelected = false
    }

    SetTopBar {
        remember(vo) {
            EventsTopBarState(
                enabled = true, showBackButton = true, currScreenAction = {
                    if (vo.isAttending) {
                        EventDetailsAction {
                            snackbarScope?.showSnackbar("${vo.name} action: ${Clock.System.now().epochSeconds % 60}")
                        }
                    }
                }, currScreenName = vo.name
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .blur(radius = EventsTheme.sizes.sizeX8, enabled = isSelected)
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(
                        horizontal = EventsTheme.sizes.sizeX9
                    ),
                    text = "${vo.date} — ${vo.location}",
                    style = EventsTheme.typography.bodyText1,
                    color = EventsTheme.colors.neutralWeak,
                )
            }

            item {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(EventsTheme.sizes.sizeX2),
                    verticalArrangement = Arrangement.spacedBy(EventsTheme.sizes.sizeX2),
                    modifier = Modifier
                        .padding(
                            horizontal = EventsTheme.sizes.sizeX9
                        )
                        .padding(top = EventsTheme.sizes.sizeX2),
                ) {
                    vo.tags.forEach { tag ->
                        RoundChip(text = tag)
                    }
                }
            }

            item {
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = EventsTheme.sizes.sizeX9)
                        .padding(top = EventsTheme.sizes.sizeX6)
                        .fillMaxWidth()
                        .aspectRatio(1.86f)
                        .noIndicationClickable { isSelected = true }
                        .clip(RoundedCornerShape(EventsTheme.sizes.sizeX12)),
                    model = vo.mapUrl,
                    contentDescription = "Map",
                )
            }

            item {
                Text(
                    text = vo.description,
                    modifier = Modifier
                        .padding(horizontal = EventsTheme.sizes.sizeX9)
                        .padding(vertical = EventsTheme.sizes.sizeX10),
                    style = EventsTheme.typography.metadata1,
                    color = EventsTheme.colors.neutralWeak,
                )
            }

        }

        AttendeesRow(
            modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
            attendees = vo.attendees,
        )

        if (vo.isAttending) {
            EventsOutlinedButton(modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX5)
                .fillMaxWidth(),
                onClick = { viewModel.cancelRegistration() }) {
                Text(text = "Maybe another time")
            }
        } else {
            EventsFilledButton(modifier = Modifier
                // Paddings are *mess*
                .padding(horizontal = EventsTheme.sizes.sizeX5)
                .fillMaxWidth(),
                onClick = { viewModel.registerOnEvent() }) {
                Text(text = "I'll go!")
            }
        }
    }
    AnimatedContent(
        isSelected, label = "Map transition",
        transitionSpec = {
            if (isSelected) {
                slideInVertically { height -> height } + fadeIn() togetherWith slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() togetherWith slideOutVertically { height -> height } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        },
    ) { showOverlay ->
        if (showOverlay) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .noIndicationClickable { isSelected = false }
                        .zoomable(state = rememberZoomableState()),
                    contentScale = ContentScale.Fit,
                    model = vo.mapUrl,
                    contentDescription = "Map",
                )
            }
        }
    }
}

@Composable
fun EventDetailsAction(onClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .size(EventsTheme.sizes.sizeX12)
            .debouncedClickable(
                interactionSource = remember {
                    MutableInteractionSource()
                }, indication = null, onClick = onClick
            ),
        painter = painterResource(id = R.drawable.icon_check),
        tint = EventsTheme.colors.brandDefault,
        contentDescription = null
    )
}
