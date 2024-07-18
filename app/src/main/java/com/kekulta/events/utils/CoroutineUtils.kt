package com.kekulta.events.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

inline fun <T, M> StateFlow<T>.map(
    coroutineScope: CoroutineScope, crossinline mapper: (value: T) -> M
): StateFlow<M> = map { mapper(it) }.stateIn(
    coroutineScope, SharingStarted.Eagerly, mapper(value)
)

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <reified T> Flow<Flow<T>>.flattenLatest(): Flow<T> = flatMapLatest { flow -> flow }

private class TransformedStateFlow<T>(
    private val getValue: () -> T,
    private val flow: Flow<T>
) : StateFlow<T> {

    override val replayCache: List<T> get() = listOf(value)
    override val value: T get() = getValue()

    override suspend fun collect(collector: FlowCollector<T>): Nothing =
        coroutineScope { flow.stateIn(this).collect(collector) }
}

/**
 * Returns [StateFlow] from [flow] having initial value from calculation of [getValue]
 */
fun <T> stateFlow(
    getValue: () -> T,
    flow: Flow<T>
): StateFlow<T> = TransformedStateFlow(getValue, flow)

/**
 * Converts [Flow] into [StateFlow] having initial value from calculation of [getValue]
 */
fun <T> Flow<T>.asStateFlow(
    getValue: () -> T,
): StateFlow<T> = stateFlow(getValue, this)

/**
 * Map [StateFlow] into another [StateFlow]
 */
@ExperimentalCoroutinesApi
fun <T, R> StateFlow<T>.stateMapLatest(mapper: (T) -> R): StateFlow<R> {
    return stateFlow(getValue = { mapper(value) }, flow = mapLatest(mapper))
}

/**
 * Combines all [stateFlows] and transforms them into another [StateFlow] with [transform]
 */
inline fun <reified T, R> combineStates(
    vararg stateFlows: StateFlow<T>,
    crossinline transform: (Array<T>) -> R
): StateFlow<R> = stateFlow(
    getValue = { transform(stateFlows.map { it.value }.toTypedArray()) },
    flow = combine(*stateFlows) { transform(it) }
)

/**
 * Variant of [combineStates] for combining 2 state flows
 */
inline fun <reified T1, reified T2, R> combineStates(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    crossinline transform: (T1, T2) -> R
) = combineStates(flow1, flow2) { (t1, t2, t3) ->
    transform(
        t1 as T1,
        t2 as T2,
    )
}

/**
 * Variant of [combineStates] for combining 3 state flows
 */
inline fun <reified T1, reified T2, reified T3, R> combineStates(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    crossinline transform: (T1, T2, T3) -> R
) = combineStates(flow1, flow2, flow3) { (t1, t2, t3) ->
    transform(
        t1 as T1,
        t2 as T2,
        t3 as T3
    )
}
