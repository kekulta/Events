package com.kekulta.events.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kekulta.events.R
import com.kekulta.events.ui.base.buttons.EventsButtonDefaults
import com.kekulta.events.ui.base.buttons.EventsOutlinedButton
import com.kekulta.events.ui.theme.EventsTheme

@Composable
fun ProfileScreen(
) {
    val profileVO = ProfileVO(
        name = "Ruslan Russkikh",
        phone = "+7 995 917-72-42",
        avatar = null
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserCircleAvatar(
            modifier = Modifier
                .padding(top = EventsTheme.sizes.sizeX16)
                .size(EventsTheme.sizes.sizeX100),
            url = profileVO.avatar,
        )
        Text(
            modifier =
            Modifier.padding(top = EventsTheme.sizes.sizeX8),
            text = profileVO.name,
            style = EventsTheme.typography.heading2
        )
        Text(
            text = profileVO.phone,
            style = EventsTheme.typography.bodyText2,
            color = EventsTheme.colors.neutralWeak,
        )
        Row(
            modifier = Modifier
                .padding(horizontal = EventsTheme.sizes.sizeX8)
                .padding(top = EventsTheme.sizes.sizeX10)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            EventsOutlinedButton(
                onClick = {},
                contentPadding = EventsButtonDefaults.iconPaddingDefaults(),
            ) {
                Icon(
                    modifier = Modifier.size(EventsTheme.sizes.sizeX8),
                    painter = painterResource(id = R.drawable.icon_twittter),
                    contentDescription = "Delete icon",
                    tint = EventsTheme.colors.brandDefault
                )
            }
            EventsOutlinedButton(
                onClick = {},
                contentPadding = EventsButtonDefaults.iconPaddingDefaults(),
            ) {
                Icon(
                    modifier = Modifier.size(EventsTheme.sizes.sizeX8),
                    painter = painterResource(id = R.drawable.icon_inst),
                    contentDescription = "Delete icon",
                    tint = EventsTheme.colors.brandDefault
                )
            }
            EventsOutlinedButton(
                onClick = {},
                contentPadding = EventsButtonDefaults.iconPaddingDefaults(),
            ) {
                Icon(
                    modifier = Modifier.size(EventsTheme.sizes.sizeX8),
                    painter = painterResource(id = R.drawable.icon_in),
                    contentDescription = "Delete icon",
                    tint = EventsTheme.colors.brandDefault
                )
            }
            EventsOutlinedButton(
                onClick = {},
                contentPadding = EventsButtonDefaults.iconPaddingDefaults(),
            ) {
                Icon(
                    modifier = Modifier.size(EventsTheme.sizes.sizeX8),
                    painter = painterResource(id = R.drawable.icon_fb),
                    contentDescription = "Delete icon",
                    tint = EventsTheme.colors.brandDefault
                )
            }
        }
    }
}

data class ProfileVO(val avatar: String?, val name: String, val phone: String)

data class Socials(
    val twitter: String?,
    val instagram: String?,
    val linkedin: String?,
    val facebook: String?,
)