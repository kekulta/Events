package com.kekulta.events.presentation.ui

import androidx.compose.runtime.MutableState

/*
    I think there is somewhere standard function like this but I can't find it yet
 */
inline fun <reified T> MutableState<T>.update(builder: T.() -> T) {
    value = value.builder()
}