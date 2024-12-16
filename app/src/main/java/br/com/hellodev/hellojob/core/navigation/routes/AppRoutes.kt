package br.com.hellodev.hellojob.core.navigation.routes

import br.com.hellodev.onboarding.core.navigation.routes.OnboardingRoutes
import kotlinx.serialization.Serializable

sealed class AppRoutes {
    @Serializable
    data object Graph: AppRoutes()
}