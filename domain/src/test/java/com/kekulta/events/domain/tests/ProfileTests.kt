package com.kekulta.events.domain.tests

import com.kekulta.events.domain.di.interactorsModule
import com.kekulta.events.domain.di.stubModules
import com.kekulta.events.domain.interactor.GetCurrentProfileInteractor
import com.kekulta.events.domain.interactor.LogOutInteractor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ProfileTests : KoinComponent {
    @Before
    fun setup() {
        startKoin { modules(stubModules, interactorsModule) }
    }

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun `Check log out`() = runTest {
        val getCurrentProfileInteractor = get<GetCurrentProfileInteractor>()
        val logOutInteractor = get<LogOutInteractor>()

        val currentProfileBefore = getCurrentProfileInteractor.execute().first()

        assertNotNull(currentProfileBefore) { "Profile must be authorized." }

        logOutInteractor.execute()

        val currentProfile = getCurrentProfileInteractor.execute().first()

        assertNull(currentProfile, "Profile must unauthorized after log out.")
    }
}