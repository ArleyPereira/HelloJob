package br.com.hellodev.hellojob.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.hellodev.onboarding.presenter.navigation.host.onboardingNavHost
import br.com.hellodev.onboarding.presenter.navigation.routes.OnboardingRoutes

@Composable
fun AppNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardingRoutes.Graph,
        //startDestination = MainRoutes.Graph,
    ) {
        //mainNavHost(navHostController = navHostController)
        onboardingNavHost(navHostController = navHostController)
    }
}