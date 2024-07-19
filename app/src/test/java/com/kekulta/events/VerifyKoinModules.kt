package com.kekulta.events

import com.kekulta.events.presentation.di.eventsAppModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

/**
 * Verify that Koin DI graph is complete.
 *
 */
class VerifyKoinModules {
    @Test
    @OptIn(KoinExperimentalAPI::class)
    fun eventsModule_verify() {
        eventsAppModule.verify()
    }
}