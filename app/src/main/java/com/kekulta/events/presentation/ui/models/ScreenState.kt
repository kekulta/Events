package com.kekulta.events.presentation.ui.models

sealed class ScreenState<T> {
    class Loading<T> : ScreenState<T>()
    data class Error<T>(val message: String?) : ScreenState<T>()
    data class Success<T>(val state: T) : ScreenState<T>()
}