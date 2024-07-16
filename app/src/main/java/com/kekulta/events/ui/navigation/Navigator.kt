package com.kekulta.events.ui.navigation

interface Navigator {
    fun setRoot(dest: Screen)
    fun navTo(dest: Screen)
    fun popBack()

    fun currTab(): Tab
}
