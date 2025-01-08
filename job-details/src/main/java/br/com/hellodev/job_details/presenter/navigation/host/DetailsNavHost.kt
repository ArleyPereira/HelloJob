package br.com.hellodev.job_details.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.job_details.presenter.features.details.screen.DetailsScreen
import br.com.hellodev.job_details.presenter.navigation.routes.DetailsRoutes

fun NavGraphBuilder.detailsNavHost(
    navHostController: NavHostController
) {
    navigation<DetailsRoutes.Graph>(
        startDestination = DetailsRoutes.Details(id = 0)
    ) {
        composable<DetailsRoutes.Details> {
            DetailsScreen(
                onBackPressed = navHostController::popBackStack
            )
        }
    }
}