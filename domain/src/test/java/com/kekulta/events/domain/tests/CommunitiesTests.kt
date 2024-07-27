package com.kekulta.events.domain.tests

import com.kekulta.events.domain.di.interactorsModule
import com.kekulta.events.domain.di.stubModules
import com.kekulta.events.domain.interactor.GetAllCommunitiesInteractor
import com.kekulta.events.domain.interactor.GetCommunityInteractor
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

        val page = getAllCommunitiesInteractor.execute(0, 50).first()

        val uniqueIds = page.values.map { community -> community.id }.toSet()

        assert(uniqueIds.size == page.size) { "All ids must be unique." }
    }

    @Test
    fun `Check community interactors return consistent data`() = runTest {
        val getAllCommunitiesInteractor = get<GetAllCommunitiesInteractor>()
        val getCommunityInteractor = get<GetCommunityInteractor>()

        val page = getAllCommunitiesInteractor.execute(0, 50).first()

        page.forEach { community ->
            assert(
                getCommunityInteractor.execute(community.id).first() == community
            ) { "GetAllCommunities and GetCommunity must return consistent data." }
        }
    }
}