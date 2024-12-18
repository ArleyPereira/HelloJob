package br.com.hellodev.setup.di

import org.koin.dsl.module

val setupModules = module {
    includes(
        presenterModule,
        useCaseModule,
        repositoryModule
    )
}