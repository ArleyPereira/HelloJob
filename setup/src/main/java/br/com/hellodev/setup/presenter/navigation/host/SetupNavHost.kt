package br.com.hellodev.setup.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.setup.presenter.features.country.screen.CountryScreen
import br.com.hellodev.setup.presenter.features.expertise.screen.ExpertiseScreen
import br.com.hellodev.setup.presenter.features.genre.screen.GenreScreen
import br.com.hellodev.setup.presenter.features.profile.screen.ProfileScreen
import br.com.hellodev.setup.presenter.navigation.routes.SetupRoutes

fun NavGraphBuilder.setupNavHost(navHostController: NavHostController) {
    navigation<SetupRoutes.Graph>(
        startDestination = SetupRoutes.Expertise
    ) {
        composable<SetupRoutes.Country> {
            CountryScreen(
                onBackPressed = navHostController::popBackStack
            )
        }

        composable<SetupRoutes.Expertise> {
            ExpertiseScreen(
                navigateToProfileScreen = {
                    navHostController.navigate(SetupRoutes.Profile)
                },
                onBackPressed = navHostController::popBackStack
            )
        }

        composable<SetupRoutes.Profile> {
            ProfileScreen(
                navigateToCountryScreen = {
                    navHostController.navigate(SetupRoutes.Country)
                },
                navigateToGenreScreen = {
                    navHostController.navigate(SetupRoutes.Genre)
                },
                onBackPressed = navHostController::popBackStack
            )
        }

        composable<SetupRoutes.Genre> {
            GenreScreen(
                onBackPressed = navHostController::popBackStack
            )
        }
    }
}