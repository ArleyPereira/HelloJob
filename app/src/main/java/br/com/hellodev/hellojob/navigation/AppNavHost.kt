package br.com.hellodev.hellojob.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.hellodev.hellojob.home.presenter.screen.HomeScreen
import br.com.hellodev.hellojob.search.presenter.screen.SearchScreen
import br.com.hellodev.onboarding.presenter.navigation.routes.OnboardingRoutes

@Composable
fun AppNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardingRoutes.Graph,
    ) {
        composable<OnboardingRoutes.Graph> {
            HomeScreen(
                navigateToSearchScreen = {
                    navHostController.navigate(OnboardingRoutes.Search(it))
                }
            )
        }

        composable<OnboardingRoutes.Search> {
            SearchScreen(
                onBackPressed = navHostController::popBackStack
            )
        }
//        onboardingNavHost(
//            navHostController = navHostController
//        )
    }

}