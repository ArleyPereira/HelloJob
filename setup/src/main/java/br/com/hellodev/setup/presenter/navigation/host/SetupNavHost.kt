package br.com.hellodev.setup.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.setup.presenter.features.country.screen.CountryScreen
import br.com.hellodev.setup.presenter.features.expertise.screen.ExpertiseScreen
import br.com.hellodev.setup.presenter.features.profile.screen.ProfileScreen
import br.com.hellodev.setup.presenter.navigation.routes.SetupRoutes

fun NavGraphBuilder.setupNavHost(navHostController: NavHostController) {
    navigation<SetupRoutes.Graph>(
        startDestination = SetupRoutes.Country
    ) {
        composable<SetupRoutes.Country> {
            CountryScreen(
                navigateToExpertiseScreen = {
                    navHostController.navigate(SetupRoutes.Expertise)
                }
            )
        }

        composable<SetupRoutes.Expertise> {
            ExpertiseScreen(
                onBackPressed = navHostController::popBackStack
            )
        }

        composable<SetupRoutes.Profile> {
            ProfileScreen(
                onBackPressed = navHostController::popBackStack
            )
        }
    }
}