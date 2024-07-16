package com.kekulta.events.presentation.ui.screens.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.kekulta.events.presentation.ui.loremIpsum
import com.kekulta.events.presentation.ui.showcase.mockAvatars
import com.kekulta.events.presentation.ui.showcase.mockEventsVo
import com.kekulta.events.presentation.ui.theme.EventsTheme
import com.kekulta.events.presentation.ui.widgets.AttendeesRow
import com.kekulta.events.presentation.ui.widgets.EventsTopBarState
import com.kekulta.events.presentation.ui.widgets.SetTopBar
import com.kekulta.events.presentation.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.presentation.ui.widgets.base.chips.RoundChip
import com.kekulta.events.presentation.ui.widgets.base.modifiers.blur
import com.kekulta.events.presentation.ui.widgets.base.modifiers.noIndicationClickable
import me.saket.telephoto.zoomable.rememberZoomableState
import me.saket.telephoto.zoomable.zoomable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventDetailsScreen(id: String) {

    /*
        Temporary solution. There will be separate vos for list and for details.
        Both will be extracted from ViewModel, not from args.
     */
    val vo = mockEventsVo(4)[3]

    var isSelected by remember {
        mutableStateOf(false)
    }

    var scale by remember { mutableFloatStateOf(1f) }

    if (!isSelected) {
        scale = 1f
    }

    BackHandler(enabled = isSelected) {
        isSelected = false
    }

    SetTopBar {
        EventsTopBarState(
            enabled = true,
            showBackButton = true,
            currScreenAction = null,
            /* Will be loaded from viewModel */
            currScreenName = "Event's Name"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .blur(radius = EventsTheme.sizes.sizeX8, enabled = isSelected)
    ) {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(
                        horizontal = EventsTheme.sizes.sizeX9
                    ),
                    text = "${vo.date} — ${vo.place}",
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
                    model = "https://i.ibb.co/Lphf2PK/map.jpg",
                    contentDescription = "Avatar",
                )
            }

            item {
                Text(
                    loremIpsum(1000),
                    modifier = Modifier
                        .padding(horizontal = EventsTheme.sizes.sizeX9)
                        .padding(vertical = EventsTheme.sizes.sizeX10),
                    style = EventsTheme.typography.metadata1,
                    color = EventsTheme.colors.neutralWeak,
                )
            }

        }


        AttendeesRow(
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX9), avatars = mockAvatars(15)
        )

        EventsFilledButton(modifier = Modifier

            /*
                 Paddings are *mess*
            */
            .padding(horizontal = EventsTheme.sizes.sizeX5)
            .fillMaxWidth(),
            onClick = { /*TODO*/ }) {
            Text(text = "I'll go!")
        }
    }
    AnimatedContent(
        isSelected, label = "basic_transition",
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
                    model = "https://i.ibb.co/Lphf2PK/map.jpg",
                    contentDescription = "Avatar",
                )
            }
        }
    }
}
