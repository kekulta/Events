import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kekulta.events.ui.elements.AttendeesRow
import com.kekulta.events.ui.elements.CommunityElement
import com.kekulta.events.ui.elements.CommunityElementVo
import com.kekulta.events.ui.elements.EventElement
import com.kekulta.events.ui.elements.EventElementVo
import com.kekulta.events.ui.theme.EventsTheme
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ShowcaseSecond(
    snackbarHostState: SnackbarHostState,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {

        val scope = rememberCoroutineScope()
        fun showSnackbar(text: String) {
            scope.launch {
                snackbarHostState.showSnackbar(text)
            }
        }

        var sliderPosition by remember { mutableFloatStateOf(12f) }
        Slider(steps = 20,
            valueRange = 0f..20f,
            value = sliderPosition,
            onValueChange = { sliderPosition = it })
        val avatars =
            List((sliderPosition + 0.1f).toInt()) { index -> if (index % 2 == 0) "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4" else null }
        AttendeesRow(modifier = Modifier.padding(24.dp), avatars = avatars)
        TempSpacer()
        mockEventsVo(10).forEachIndexed { index, vo ->
            EventElement(modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
                eventVo = vo,
                onClick = { showSnackbar("Meeting ${vo.name} clicked!") })
            Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX6))
            if (index != 9) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
                    color = EventsTheme.colors.neutralLine,
                )
                Spacer(modifier = Modifier.size(EventsTheme.sizes.sizeX6))
            }
        }
        TempSpacer()
        mockCommunitiesVo(10).forEachIndexed { index, vo ->
            CommunityElement(modifier = Modifier.padding(horizontal = EventsTheme.sizes.sizeX9),
                communityVo = vo,
                onClick = { showSnackbar("Community ${vo.name} clicked!") })
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
}


fun mockEventsVo(size: Int): List<EventElementVo> {
    val places = listOf("Moscow", "SPb", "Kazan", "Tbilisi")
    val names = listOf("Developer Meeting", "Code'n'code", "Mobile Submarine", "Mobius")
    val tags = listOf("Mobile", "Junior", "Middle", "Senior", "Kotlin", "Android", "Java", "LISP")

    return List(size) { index ->
        EventElementVo(name = names[index % names.size],
            avatar = if (index % 2 == 0) "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4" else null,
            date = LocalDateTime.now().plusDays(index.toLong())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            note = if (index % 2 == 0) "Event is over" else null,
            place = places[index % places.size],
            tags = List(index % tags.size) { tagIndex -> tags[(index + tagIndex) % tags.size] })
    }
}


fun mockCommunitiesVo(size: Int): List<CommunityElementVo> {
    val names = listOf("Developer Meeting", "Code'n'code", "Mobile Submarine", "Mobius")

    return List(size) { index ->
        CommunityElementVo(
            name = names[index % names.size],
            avatar = if (index % 2 == 0) "https://avatars.githubusercontent.com/u/33986203?s=400&u=e890dc6a3d5835a8d26850faec9a0095809a3243&v=4" else null,
            members = "$index members",
        )
    }
}
