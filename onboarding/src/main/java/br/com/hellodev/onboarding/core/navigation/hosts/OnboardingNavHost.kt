package br.com.hellodev.onboarding.core.navigation.hosts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.authentication.core.navigation.hosts.authenticationNavHost
import br.com.hellodev.authentication.core.navigation.routes.AuthenticationRoutes
import br.com.hellodev.onboarding.core.navigation.routes.OnboardingRoutes
import br.com.hellodev.onboarding.presenter.features.onboarding.screen.WelcomeScreen

fun NavGraphBuilder.onboardingNavHost(
    navHostController: NavHostController
) {
    navigation<OnboardingRoutes.Graph>(
        startDestination = OnboardingRoutes.Welcome
    ) {
        composable<OnboardingRoutes.Welcome> {
            WelcomeScreen(
                navigateToAuthentication = {
                    navHostController.navigate(AuthenticationRoutes.Graph)
                }
            )
        }
    }

    authenticationNavHost(navHostController = navHostController)
}