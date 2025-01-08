package br.com.hellodev.main.di

import br.com.hellodev.main.presenter.features.home.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presenterModule = module {

    viewModelOf(::HomeViewModel)

}