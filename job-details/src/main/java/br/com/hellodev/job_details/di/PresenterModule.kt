package br.com.hellodev.job_details.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import br.com.hellodev.job_details.presenter.features.details.viewmodel.DetailsViewModel

val presenterModule = module {

    viewModelOf(::DetailsViewModel)

}