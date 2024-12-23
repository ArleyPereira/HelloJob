package br.com.hellodev.setup.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.core.constants.navigation.PROFILE_SCREEN_KEY
import br.com.hellodev.core.extensions.popBackStackSafely
import br.com.hellodev.core.serialization.getObject
import br.com.hellodev.core.serialization.putObject
import br.com.hellodev.setup.presenter.features.country.screen.CountryScreen
import br.com.hellodev.setup.presenter.features.expertise.screen.ExpertiseScreen
import br.com.hellodev.setup.presenter.features.genre.screen.GenreScreen
import br.com.hellodev.setup.presenter.features.profile.parameter.ProfileParameter
import br.com.hellodev.setup.presenter.features.profile.screen.ProfileScreen
import br.com.hellodev.setup.presenter.navigation.routes.SetupRoutes

fun NavGraphBuilder.setupNavHost(navHostController: NavHostController) {
    navigation<SetupRoutes.Graph>(
        startDestination = SetupRoutes.Expertise
    ) {
        composable<SetupRoutes.Country> {
            CountryScreen(
                onBackPressed = { country ->
                    val profileParameter = ProfileParameter(country = country)
                    navHostController.previousBackStackEntry?.savedStateHandle?.putObject(
                        PROFILE_SCREEN_KEY,
                        profileParameter
                    )
                    navHostController.popBackStackSafely()
                }
            )
        }

        composable<SetupRoutes.Expertise> {
            ExpertiseScreen(
                navigateToProfileScreen = {
                    navHostController.navigate(SetupRoutes.Profile())
                },
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        composable<SetupRoutes.Profile> { backStackEntry ->
            val parameter = backStackEntry.savedStateHandle.getObject<ProfileParameter>(PROFILE_SCREEN_KEY)
            ProfileScreen(
                parameter = parameter,
                navigateToCountryScreen = {
                    navHostController.navigate(SetupRoutes.Country)
                },
                navigateToGenreScreen = {
                    navHostController.navigate(SetupRoutes.Genre)
                },
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        composable<SetupRoutes.Genre> {
            GenreScreen(
                onBackPressed = { genre ->
                    val profileParameter = ProfileParameter(genre = genre)
                    navHostController.previousBackStackEntry?.savedStateHandle?.putObject(
                        PROFILE_SCREEN_KEY,
                        profileParameter
                    )
                    navHostController.popBackStackSafely()
                }
            )
        }

    }
}