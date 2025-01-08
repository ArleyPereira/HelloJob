package br.com.hellodev.common.di

import org.koin.dsl.module

val commonModules = module {
    includes(
        repositoryModule
    )
}