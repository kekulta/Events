package com.kekulta.events.domain.tests

import com.kekulta.events.domain.di.interactorsModule
import com.kekulta.events.domain.di.stubModules
import com.kekulta.events.domain.interactor.GetAllCommunitiesInteractor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class CommunitiesTests : KoinComponent {
    @Before
    fun setup() {
        startKoin { modules(stubModules, interactorsModule) }
    }

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun `Check community ids uniqueness`() = runTest {
        val getAllCommunitiesInteractor = get<GetAllCommunitiesInteractor>()

        val page = getAllCommunitiesInteractor.execute(0, 0).first()

        val uniqueIds = page.values.map { community -> community.id }.toSet()

        assert(uniqueIds.size == page.size) { "All ids must be unique." }
    }
}