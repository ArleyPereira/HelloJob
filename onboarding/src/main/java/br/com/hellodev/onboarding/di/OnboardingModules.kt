package br.com.hellodev.onboarding.di

import org.koin.dsl.module

val onboardingModules = module {
    includes(
        presenterModule,
        useCaseModule,
        repositoryModule
    )
}