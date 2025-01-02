package br.com.hellodev.hellojob.home.di

import org.koin.dsl.module

val homeModules = module {
    includes(
        presenterModule
    )
}