package br.com.hellodev.job_apply.di

import br.com.hellodev.job_details.presenter.features.applying.viewmodel.ApplyingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presenterModule = module {

    viewModelOf(::ApplyingViewModel)

}