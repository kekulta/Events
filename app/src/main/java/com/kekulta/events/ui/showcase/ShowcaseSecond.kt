package com.kekulta.events.ui.showcase

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.theme.EventsTheme
import com.kekulta.events.ui.widgets.AttendeesRow
import com.kekulta.events.ui.widgets.EventElement
import com.kekulta.events.ui.widgets.EventElementVo
import com.kekulta.events.ui.widgets.GroupElement
import com.kekulta.events.ui.widgets.GroupElementVo
import com.kekulta.events.ui.widgets.GroupId
import com.kekulta.events.ui.widgets.base.snackbar.SnackbarScope
import com.kekulta.events.ui.widgets.base.snackbar.showSnackbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LazyListScope.showcaseSecond(
    snackbarScope: SnackbarScope,
) {
    item {
        var sliderPosition by remember { mutableFloatStateOf(12f) }
        Slider(steps = 20,
            valueRange = 0f..20f,
            value = sliderPosition,
            onValueChange = { sliderPosition = it })
        val avatars =
            List((sliderPosition + 0.1f).toInt()) { index -> if (index % 2 == 0) "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4" else null }
        AttendeesRow(modifier = Modifier.padding(EventsTheme.sizes.sizeX12), avatars = avatars)
        TempSpacer()
    }

    itemsIndexed(mockEventsVo(10)) { index, vo ->
        EventElement(modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX9)
            .fillMaxWidth(),
            eventVo = vo,
            onClick = { snackbarScope.showSnackbar("Meeting ${vo.name} clicked!") })
        Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX6))
        if (index != 9) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
                color = EventsTheme.colors.neutralLine,
            )
            Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX6))
        } else {
            TempSpacer()
        }
    }

    itemsIndexed(mockGroupsVo(10)) { index, vo ->
        GroupElement(modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX9)
            .fillMaxWidth(),
            groupVo = vo,
            onClick = { snackbarScope.showSnackbar("Community ${vo.name} clicked!") })
        Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX6))
        if (index != 9) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
                color = EventsTheme.colors.neutralLine,
            )
            Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX6))
        }
    }
}


fun mockEventsVo(size: Int): List<EventElementVo> {
    val places = listOf("Moscow", "SPb", "Kazan", "Tbilisi")
    val names = listOf(
        "Developer Meeting",
        "Code'n'code",
        "Mobile Submarine",
        "Mobius",
        "Very long name of the software event Conf."
    )
    val tags = listOf("Mobile", "Junior", "Middle", "Senior", "Kotlin", "Android", "Java", "LISP")

    return List(size) { index ->
        EventElementVo(name = names[index % names.size],
            avatar = if (index % 2 == 0) "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4" else null,
            date = LocalDateTime.now().plusDays(index.toLong())
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            note = if (index % 2 == 0) "Event is over" else null,
            place = places[index % places.size],
            tags = List(index % tags.size) { tagIndex -> tags[(index + tagIndex) % tags.size] })
    }
}


fun mockGroupsVo(size: Int): List<GroupElementVo> {
    val names = listOf("Developer Meeting", "Code'n'code", "Mobile Submarine", "Mobius")

    return List(size) { index ->
        GroupElementVo(
            id = GroupId(System.currentTimeMillis().toString()),
            name = names[index % names.size],
            avatar = if (index % 2 == 0) "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4" else null,
            members = "$index members",
        )
    }
}
