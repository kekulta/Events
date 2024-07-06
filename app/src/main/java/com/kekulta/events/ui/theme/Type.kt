package com.kekulta.events.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
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


val EventsTypographyValue = EventsTypography(
    heading1 = TextStyle(
        fontFamily = sfProFontFamily, fontWeight = FontWeight.Bold, fontSize = 32.sp * scaleFactor,
        lineHeight = 38.sp * scaleFactor,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    heading2 = TextStyle(
        fontFamily = sfProFontFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp * scaleFactor,
        lineHeight = 28.sp * scaleFactor,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    subheading1 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp * scaleFactor,
        lineHeight = 30.sp * scaleFactor,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    subheading2 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp * scaleFactor,
        lineHeight = 28.sp * scaleFactor,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    bodyText1 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp * scaleFactor,
        lineHeight = 24.sp * scaleFactor,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    bodyText2 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp * scaleFactor,
        lineHeight = 24.sp * scaleFactor,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    metadata1 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp * scaleFactor,
        lineHeight = 20.sp * scaleFactor,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    metadata2 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp * scaleFactor,
        lineHeight = 16.sp * scaleFactor,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    metadata3 = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp * scaleFactor,
        lineHeight = 16.sp * scaleFactor,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
)

val LocalTypography = staticCompositionLocalOf<EventsTypography> {
    error("No typography provided!")
}

