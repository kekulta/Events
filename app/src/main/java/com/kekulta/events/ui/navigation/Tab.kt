package com.kekulta.events.ui.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Tab : Parcelable {

    /*
        Main three Tabs
     */
    EVENTS, GROUPS, MORE,

    /*
        No Tab will be selected
     */
    NO_TAB,

    /*
        Whole nav bar will be hidden
     */
    NO_BAR,
}
