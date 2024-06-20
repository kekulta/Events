package com.kekulta.events.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kekulta.events.R

val sfProFontFamily = FontFamily(
    Font(R.font.sfprodisplayblackitalic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.sfprodisplaybold, FontWeight.Bold),
    Font(R.font.sfprodisplayheavyitalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.sfprodisplaylightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.sfprodisplaymedium, FontWeight.Medium),
    Font(R.font.sfprodisplayregular, FontWeight.Normal),
    Font(R.font.sfprodisplaysemibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.sfprodisplayultralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.sfprodisplaythinitalic, FontWeight.Thin, FontStyle.Italic),
)

@Immutable
data class EventsTypography(
    val heading1: TextStyle,
    val heading2: TextStyle,
    val subheading1: TextStyle,
    val subheading2: TextStyle,
    val bodyText1: TextStyle,
    val bodyText2: TextStyle,
    val metadata1: TextStyle,
    val metadata2: TextStyle,
    val metadata3: TextStyle,
)

private val scaleFactor = 1.2

val EventsTypographyValue = EventsTypography(
    heading1 = TextStyle(
        fontFamily = sfProFontFamily, fontWeight = FontWeight.Bold, fontSize = 32.sp * scaleFactor,
    ),
    heading2 = TextStyle(
        fontFamily = sfProFontFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp * scaleFactor
    ),
    subheading1 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp * scaleFactor
    ),
    subheading2 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp * scaleFactor
    ),
    bodyText1 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp * scaleFactor
    ),
    bodyText2 = TextStyle(
        fontFamily = sfProFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp * scaleFactor
    ),
    metadata1 = TextStyle(
        fontFamily = sfProFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp * scaleFactor
    ),
    metadata2 = TextStyle(
        fontFamily = sfProFontFamily, fontWeight = FontWeight.Normal, fontSize = 10.sp * scaleFactor
    ),
    metadata3 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp * scaleFactor,
    ),
)

val LocalEventsTypography = staticCompositionLocalOf {
    EventsTypographyValue
}

