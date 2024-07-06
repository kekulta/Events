package com.kekulta.events.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

const val unit = 2

@Immutable
data class EventsSizeSystem(
    val sizeX1: Dp,
    val sizeX2: Dp,
    val sizeX3: Dp,
    val sizeX4: Dp,
    val sizeX5: Dp,
    val sizeX6: Dp,
    val sizeX7: Dp,
    val sizeX8: Dp,
    val sizeX9: Dp,
    val sizeX10: Dp,
    val sizeX12: Dp,
    val sizeX16: Dp,
    val sizeX20: Dp,
    val sizeX18: Dp,
    val sizeX24: Dp,
    val sizeX25: Dp,
    val sizeX26: Dp,
    val sizeX50: Dp,
    val sizeX100: Dp,
)

val EventsLocalSizeSystem = EventsSizeSystem(
    sizeX1 = scaleFactor * unit * 1.dp,
    sizeX2 = scaleFactor * unit * 2.dp,
    sizeX3 = scaleFactor * unit * 3.dp,
    sizeX4 = scaleFactor * unit * 4.dp,
    sizeX5 = scaleFactor * unit * 5.dp,
    sizeX6 = scaleFactor * unit * 6.dp,
    sizeX7 = scaleFactor * unit * 7.dp,
    sizeX8 = scaleFactor * unit * 8.dp,
    sizeX9 = scaleFactor * unit * 9.dp,
    sizeX10 = scaleFactor * unit * 10.dp,
    sizeX12 = scaleFactor * unit * 12.dp,
    sizeX16 = scaleFactor * unit * 16.dp,
    sizeX20 = scaleFactor * unit * 20.dp,
    sizeX18 = scaleFactor * unit * 18.dp,
    sizeX24 = scaleFactor * unit * 24.dp,
    sizeX25 = scaleFactor * unit * 25.dp,
    sizeX26 = scaleFactor * unit * 26.dp,
    sizeX50 = scaleFactor * unit * 50.dp,
    sizeX100 = scaleFactor * unit * 100.dp,
)

val LocalSizeSystem = staticCompositionLocalOf<EventsSizeSystem> {
    error("No size system provided!")
 }
