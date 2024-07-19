package com.kekulta.events.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class AbstractCoroutineViewModel : ViewModel() {
    private val holder = mutableMapOf<Any, Job>()

    fun launchScope(
        scopeId: Any = this,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ) {
        holder[scopeId]?.cancel()
        holder[scopeId] = viewModelScope.launch(dispatcher) {
            block()
        }
    }

    fun cancelScope(scopeId: Any): Boolean {
        return if (holder[scopeId] == null) {
            false
        } else {
            holder[scopeId]?.cancel()
            true
        }
    }

    /*
        Defined here and not as an extension because I need both ViewModel context and
        Flow as receiver. Context receivers would be really helpful here.
     */
    fun <T> Flow<T>.asStateFlow(initialValue: T): StateFlow<T> {
        return stateIn(viewModelScope, SharingStarted.Eagerly, initialValue)
    }

    override fun onCleared() {
        super.onCleared()
        holder.forEach { (_, job) -> job.cancel() }
    }
}
