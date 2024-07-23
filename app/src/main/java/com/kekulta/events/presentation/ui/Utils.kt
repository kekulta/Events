package com.kekulta.events.presentation.ui

import androidx.compose.runtime.MutableState

/**
 *  Updates MutableState with the value computed from the [builder]
 *  I think There must be library function instead of this.
 */
inline fun <reified T> MutableState<T>.update(builder: T.() -> T) {
    value = value.builder()
}