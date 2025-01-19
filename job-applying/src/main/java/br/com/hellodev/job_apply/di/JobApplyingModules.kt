package br.com.hellodev.job_apply.di

import org.koin.dsl.module

val jobApplyingModules = module {
    includes(
        presenterModule
    )
}