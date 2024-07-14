package com.kekulta.events.ui.widgets.base.data

import androidx.annotation.DrawableRes
import com.kekulta.events.R

data class Country(
    val country: String,
    val countryCode: String,
    @DrawableRes val flagIcon: Int,
)

object Countries {
    val russia = Country(
        "russia",
        countryCode = "+7",
        R.drawable.icon_flag_russia
    )
    val kazakhstan = Country(
        "kazakhstan",
        countryCode = "+7",
        R.drawable.icon_flag_kazakhstan
    )
    val uzbekistan =
        Country(
            "uzbekistan",
            countryCode = "+998",
            R.drawable.icon_flag_uzbekistan
        )
    val tajikistan =
        Country(
            "tajikistan",
            countryCode = "+996",
            R.drawable.icon_flag_tajikistan
        )
    val georgia =
        Country(
            "georgia",
            countryCode = "+995",
            R.drawable.icon_flag_georgia
        )
    val turkey =
        Country(
            "turkey", countryCode = "+90",
            R.drawable.icon_flag_turkey
        )
    val usa = Country(
        "usa",
        countryCode = "+1",
        R.drawable.icon_flag_usa
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
