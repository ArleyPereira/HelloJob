package br.com.hellodev.hellojob.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.hellodev.onboarding.presenter.navigation.host.onboardingNavHost
import br.com.hellodev.onboarding.presenter.navigation.routes.OnboardingRoutes

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardingRoutes.Graph,
        modifier = modifier
    ) {
        onboardingNavHost(
            navHostController = navHostController
        )
    }

}