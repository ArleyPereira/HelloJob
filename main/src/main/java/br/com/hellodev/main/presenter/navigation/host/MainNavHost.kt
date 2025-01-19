package br.com.hellodev.main.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.core.extensions.popBackStackSafely
import br.com.hellodev.job_details.presenter.navigation.host.detailsNavHost
import br.com.hellodev.job_details.presenter.navigation.routes.DetailsRoutes
import br.com.hellodev.job_search.presenter.navigation.host.searchNavHost
import br.com.hellodev.job_search.presenter.navigation.routes.SearchRoutes
import br.com.hellodev.main.presenter.features.applications.list.screen.ApplicationListScreen
import br.com.hellodev.main.presenter.features.applications.status.screen.ApplicationStatusScreen
import br.com.hellodev.main.presenter.features.home.screen.HomeScreen
import br.com.hellodev.main.presenter.features.saved.screen.SavedScreen
import br.com.hellodev.main.presenter.navigation.routes.MainRoutes

fun NavGraphBuilder.mainNavHost(
    navHostController: NavHostController
) {
    navigation<MainRoutes.Graph>(
        startDestination = MainRoutes.ApplicationList
    ) {
        composable<MainRoutes.Home> {
            HomeScreen(
                navigateToDetails = {
                    navHostController.navigate(DetailsRoutes.Details(id = it))
                },
                navigateToSearchScreen = {
                    navHostController.navigate(SearchRoutes.Search(it))
                }
            )
        }

        composable<MainRoutes.Saved> {
            SavedScreen(
                navigateToDetails = {
                    navHostController.navigate(DetailsRoutes.Details(id = it))
                }
            )
        }

        composable<MainRoutes.ApplicationList> {
            ApplicationListScreen(
                navigateToApplicationStatusScreen = {
                    navHostController.navigate(MainRoutes.ApplicationStatus(id = 1))
                }
            )
        }

        composable<MainRoutes.ApplicationStatus> {
            ApplicationStatusScreen(
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        searchNavHost(navHostController = navHostController)

        detailsNavHost(navHostController = navHostController)
    }
}