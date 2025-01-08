package br.com.hellodev.job_search.di

import org.koin.dsl.module

val jobSearchModules = module {
    includes(
        presenterModule
    )
}