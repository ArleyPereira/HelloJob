package br.com.hellodev.hellojob.core.navigation.hosts

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.hellodev.onboarding.core.navigation.hosts.onboardingNavHost
import br.com.hellodev.onboarding.core.navigation.routes.OnboardingRoutes

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardingRoutes.Graph
    ) {
        onboardingNavHost(navHostController = navHostController)
    }

}