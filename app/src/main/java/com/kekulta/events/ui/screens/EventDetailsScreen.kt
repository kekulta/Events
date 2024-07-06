package com.kekulta.events.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.kekulta.events.ui.loremIpsum
import com.kekulta.events.ui.showcase.mockAvatars
import com.kekulta.events.ui.showcase.mockEventsVo
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.AttendeesRow
import com.kekulta.events.ui.widgets.base.buttons.EventsFilledButton
import com.kekulta.events.ui.widgets.base.chips.RoundChip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventDetailsScreen(id: String) {/*
        Temporary solution. There will be separate vos for list and for details.
     */
    val vo = mockEventsVo(4)[3]

    LazyColumn(
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
                    .height(EventsTheme.sizes.sizeX100)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(EventsTheme.sizes.sizeX12)),
                model = "https://i.ibb.co/Lphf2PK/map.jpg",
                contentDescription = "Avatar",
            )
        }

        item {
            Text(
                loremIpsum(),
                modifier = Modifier
                    .padding(horizontal = EventsTheme.sizes.sizeX9)
                    .padding(top = EventsTheme.sizes.sizeX10),
                style = EventsTheme.typography.metadata1,
                color = EventsTheme.colors.neutralWeak,
            )
        }

        item {
            AttendeesRow(
                modifier = Modifier
                    .padding(horizontal = EventsTheme.sizes.sizeX9)
                    .padding(top = EventsTheme.sizes.sizeX10),
                avatars = mockAvatars(15)
            )
        }

        item {
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
    }
}