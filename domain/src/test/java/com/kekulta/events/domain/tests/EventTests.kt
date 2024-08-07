package com.kekulta.events.domain.tests

import com.kekulta.events.common.utils.isFuture
import com.kekulta.events.common.utils.isPast
import com.kekulta.events.common.utils.isToday
import com.kekulta.events.domain.di.interactorsModule
import com.kekulta.events.domain.di.stubModules
import com.kekulta.events.domain.interactor.CancelEventRegistrationInteractor
import com.kekulta.events.domain.interactor.GetActiveEventsInteractor
import com.kekulta.events.domain.interactor.GetAllEventsInteractor
import com.kekulta.events.domain.interactor.GetMyPastEventsInteractor
import com.kekulta.events.domain.interactor.GetMyPlannedEventsInteractor
import com.kekulta.events.domain.interactor.IsRegisteredToEventInteractor
import com.kekulta.events.domain.interactor.RegisterToEventInteractor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class EventTests : KoinComponent {
    @Before
    fun setup() {
        startKoin { modules(stubModules, interactorsModule) }
    }

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun `Check event ids uniqueness`() = runTest {
        val getAllEventsInteractor = get<GetAllEventsInteractor>()

        val page = getAllEventsInteractor.execute(0, 50).first()

        val uniqueIds = page.values.map { event -> event.id }.toSet()

        assert(uniqueIds.size == page.size) { "All ids must be unique." }
    }

    @Test
    fun `Check registration to event`() = runTest {
        val getAllEventsInteractor = get<GetAllEventsInteractor>()
        val isRegisteredToEventInteractor = get<IsRegisteredToEventInteractor>()
        val registerToEventInteractor = get<RegisterToEventInteractor>()
        val page = getAllEventsInteractor.execute(0, 1).first()

        val event = page.first()

        val isRegisteredBefore = isRegisteredToEventInteractor.execute(event.id).first()

        assert(!isRegisteredBefore) { "Default profile must have zero registration." }

        registerToEventInteractor.execute(event.id)

        val isRegisteredAfter = isRegisteredToEventInteractor.execute(event.id).first()

        assert(isRegisteredAfter) { "Profile must be registered after Interactor executes." }
    }

    @Test
    fun `Check cancel registration to event`() = runTest {
        val getAllEventsInteractor = get<GetAllEventsInteractor>()
        val isRegisteredToEventInteractor = get<IsRegisteredToEventInteractor>()
        val registerToEventInteractor = get<RegisterToEventInteractor>()
        val cancelRegisterToEventInteractor = get<CancelEventRegistrationInteractor>()
        val page = getAllEventsInteractor.execute(0, 1).first()

        val event = page.first()

        val isRegisteredBefore = isRegisteredToEventInteractor.execute(event.id).first()

        assert(!isRegisteredBefore) { "Default profile must have zero registration." }

        registerToEventInteractor.execute(event.id)

        val isRegisteredAfter = isRegisteredToEventInteractor.execute(event.id).first()

        assert(isRegisteredAfter) { "Profile must be registered after Interactor executes." }

        cancelRegisterToEventInteractor.execute(event.id)

        val isRegisteredAfterCancel = isRegisteredToEventInteractor.execute(event.id).first()

        assert(!isRegisteredAfterCancel) { "Profile must not be registered after cancel." }
    }

    @Test
    fun `Check Active Events interactor return only active events`() = runTest {
        val activeEventsInteractor = get<GetActiveEventsInteractor>()

        val page = activeEventsInteractor.execute(0, 50).first()

        page.forEach { event ->
            println(event.date)
            assert(event.date.date.isToday())
        }
    }

    @Test
    fun `Check GetMyPastEventsInteractor return only past events`() = runTest {
        val getMyPastEventsInteractor = get<GetMyPastEventsInteractor>()
        val getAllEventsInteractor = get<GetAllEventsInteractor>()
        val registerToEventInteractor = get<RegisterToEventInteractor>()

        val allEvents = getAllEventsInteractor.execute(0, 50).first()
        allEvents.forEach { event -> registerToEventInteractor.execute(event.id) }

        val pastEvents = getMyPastEventsInteractor.execute(0, 50).first()

        pastEvents.forEach { event -> assert(event.date.date.isPast()) { "All returned events must be in past." } }
    }

    @Test
    fun `Check GetMyPlannedEventsInteractor return only past events`() = runTest {
        val getMyPlannedEventsInteractor = get<GetMyPlannedEventsInteractor>()
        val getAllEventsInteractor = get<GetAllEventsInteractor>()
        val registerToEventInteractor = get<RegisterToEventInteractor>()

        val allEvents = getAllEventsInteractor.execute(0, 50).first()
        allEvents.forEach { event -> registerToEventInteractor.execute(event.id) }

        val pastEvents = getMyPlannedEventsInteractor.execute(0, 50).first()

        pastEvents.forEach { event -> assert(event.date.date.isFuture() || event.date.date.isToday()) { "All returned events must be in future or today." } }
    }

}