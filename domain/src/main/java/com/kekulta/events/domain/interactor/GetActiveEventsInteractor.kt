package com.kekulta.events.domain.interactor

import com.kekulta.events.domain.models.EventModel
import kotlinx.coroutines.flow.Flow

interface GetActiveEventsInteractor {
    /*
        I do not use `operator fun invoke()` on purpose. I prefer explicit function over implicit
        one. Operator overloading may cause confusion and should used very carefully.
        I don't think that interactors are the place to use them.
     */
    fun execute(): Flow<List<EventModel>>
}
