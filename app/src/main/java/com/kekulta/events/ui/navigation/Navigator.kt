package com.kekulta.events.ui.navigation

interface Navigator {
    fun navTo(dest: Screen)
    fun popBack()

    fun currTab(): Tab
}
