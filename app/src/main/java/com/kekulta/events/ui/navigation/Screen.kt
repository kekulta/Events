package com.kekulta.events.ui.navigation

interface Screen {
    val tab: Tab
    /*
        Unlocalizable. I should do something with it, but passing res ids around doesn't look good
        either.
    */
    val name: String
    val isRoot: Boolean
}
