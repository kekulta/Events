package com.kekulta.events.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

fun <T, M> StateFlow<T>.map(
    coroutineScope: CoroutineScope, mapper: (value: T) -> M
): StateFlow<M> = map { mapper(it) }.stateIn(
    coroutineScope, SharingStarted.Eagerly, mapper(value)
)

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <reified T> Flow<Flow<T>>.flattenLatest(): Flow<T> = flatMapLatest { flow -> flow }


