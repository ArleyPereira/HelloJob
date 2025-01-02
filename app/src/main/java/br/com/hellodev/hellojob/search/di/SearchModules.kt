package br.com.hellodev.hellojob.search.di

import org.koin.dsl.module

val searchModules = module {
    includes(
        presenterModule
    )
}