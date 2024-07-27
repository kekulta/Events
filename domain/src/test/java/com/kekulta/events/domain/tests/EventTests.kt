package com.kekulta.events.domain.tests

import com.kekulta.events.domain.di.interactorsModule
import com.kekulta.events.domain.di.stubModules
import com.kekulta.events.domain.interactor.CancelEventRegistrationInteractor
import com.kekulta.events.domain.interactor.GetAllEventsInteractor
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

        val page = getAllEventsInteractor.execute(0, 0).first()

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
}