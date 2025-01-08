package br.com.hellodev.authentication.di

import org.koin.dsl.module

val authenticationModules = module {
    includes(
        presenterModule,
        useCaseModule,
        repositoryModule
    )
}