package com.kekulta.events

import android.app.Application
import com.kekulta.events.di.formattersModule
import com.kekulta.events.di.repositoriesModule
import com.kekulta.events.di.servicesModule
import com.kekulta.events.di.usecaseModule
import com.kekulta.events.di.viewModelsModule
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import logcat.LogcatLogger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        /*
            Install on all build variants, because I test in release build.
            Animations won't work properly otherwise.
         */
        LogcatLogger.install(AndroidLogcatLogger(LogPriority.VERBOSE))

        startKoin {
            androidContext(this@App)
            modules(
                viewModelsModule,
                usecaseModule,
                formattersModule,
                servicesModule,
                repositoriesModule,
            )
        }
    }
}