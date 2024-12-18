package br.com.hellodev.di

import br.com.hellodev.authentication.di.authenticationModules
import br.com.hellodev.core.di.coreModules
import br.com.hellodev.onboarding.di.onboardingModules
import br.com.hellodev.setup.di.setupModules
import org.koin.dsl.module

val appModules = module {
    includes(
        coreModules,
        onboardingModules,
        authenticationModules,
        setupModules
    )
}