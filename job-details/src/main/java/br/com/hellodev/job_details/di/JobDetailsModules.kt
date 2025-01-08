package br.com.hellodev.job_details.di

import org.koin.dsl.module

val jobDetailsModules = module {
    includes(
        presenterModule
    )
}