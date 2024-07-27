package com.kekulta.events.domain.tests

import com.kekulta.events.domain.di.stubModules
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify


class VerifyKoinModules {
    @Test
    @OptIn(KoinExperimentalAPI::class)
    fun `Verify Stub Koin modules`() {
        stubModules.verify()
    }
}
