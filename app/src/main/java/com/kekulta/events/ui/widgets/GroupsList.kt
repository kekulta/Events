package com.kekulta.events.ui.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import com.kekulta.events.ui.theme.EventsTheme

fun LazyListScope.groupsList(
    groups: List<GroupElementVo>, onClick: ((GroupId) -> Unit)? = null
) {
    itemsIndexed(groups) { index, vo ->
        GroupElement(modifier = Modifier
            .padding(horizontal = EventsTheme.sizes.sizeX9)
            .fillMaxWidth(),
            groupVo = vo,
            onClick = { onClick?.invoke(vo.id) })
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
