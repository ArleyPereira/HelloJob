package br.com.hellodev.di

import br.com.hellodev.authentication.di.authenticationModules
import br.com.hellodev.common.di.commonModules
import br.com.hellodev.core.di.coreModules
import br.com.hellodev.job_details.di.jobDetailsModules
import br.com.hellodev.job_search.di.jobSearchModules
import br.com.hellodev.main.di.mainModules
import br.com.hellodev.onboarding.di.onboardingModules
import br.com.hellodev.profile.di.profileModules
import br.com.hellodev.setup.di.setupModules
import org.koin.dsl.module

val appModules = module {
    includes(
        coreModules,
        commonModules,
        onboardingModules,
        authenticationModules,
        setupModules,
        mainModules,
        jobSearchModules,
        jobDetailsModules,
        profileModules
    )
}