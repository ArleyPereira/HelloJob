package br.com.hellodev.main.di

import org.koin.dsl.module

val mainModules = module {
    includes(
        presenterModule
    )
}