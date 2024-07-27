package com.kekulta.events.presentation.ui.widgets.base.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kekulta.events.R

data class Country(
    @StringRes val country: Int,
    @DrawableRes val flagIcon: Int,
    val countryCode: String,
    val prefix: String,
)

object Countries {
    val russia = Country(
        R.string.russia,
        R.drawable.icon_flag_russia,
        countryCode = "RU",
        prefix = "+7",
    )
    val kazakhstan = Country(
        R.string.kazakhstan,
        R.drawable.icon_flag_kazakhstan,
        countryCode = "KZ",
        prefix = "+7",
    )
    val uzbekistan = Country(
        R.string.uzbekistan,
        R.drawable.icon_flag_uzbekistan,
        countryCode = "UZ",
        prefix = "+998",
    )
    val tajikistan = Country(
        R.string.tajikistan,
        R.drawable.icon_flag_tajikistan,
        countryCode = "TJ",
        prefix = "+996",
    )
    val georgia = Country(
        R.string.georgia,
        R.drawable.icon_flag_georgia,
        countryCode = "GE",
        prefix = "+995",
    )
    val turkey = Country(
        R.string.turkey,
        R.drawable.icon_flag_turkey,
        countryCode = "TR",
        prefix = "+90",
    )
    val usa = Country(
        R.string.usa,
        R.drawable.icon_flag_usa,
        countryCode = "US",
        prefix = "+1",
    )

    val countries = listOf(
        russia,
        kazakhstan,
        uzbekistan,
        tajikistan,
        georgia,
        turkey,
        usa,
    )
}
