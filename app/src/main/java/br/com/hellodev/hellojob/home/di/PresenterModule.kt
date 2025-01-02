package br.com.hellodev.hellojob.home.di

import br.com.hellodev.hellojob.home.presenter.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presenterModule = module {

    viewModelOf(::HomeViewModel)

}