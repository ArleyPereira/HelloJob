package br.com.hellodev.hellojob.core.application

import android.app.Application
import br.com.hellodev.authentication.di.authenticationModules
import br.com.hellodev.onboarding.di.onboardingModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                onboardingModules,
                authenticationModules,
            )
        }
    }
}