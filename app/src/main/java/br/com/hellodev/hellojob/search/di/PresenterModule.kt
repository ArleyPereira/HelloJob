package br.com.hellodev.hellojob.search.di

import br.com.hellodev.hellojob.search.presenter.viewmodel.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presenterModule = module {

    viewModelOf(::SearchViewModel)

}