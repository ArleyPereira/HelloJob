package br.com.hellodev.main.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.hellodev.main.presenter.features.home.screen.HomeScreen
import br.com.hellodev.main.presenter.features.search.screen.SearchScreen
import br.com.hellodev.main.presenter.navigation.routes.MainRoutes

fun NavGraphBuilder.mainNavHost(
    navHostController: NavHostController
) {
    navigation<MainRoutes.Graph>(
        startDestination = MainRoutes.Home
    ) {
        composable<MainRoutes.Home> {
            HomeScreen(
                navigateToSearchScreen = {
                    navHostController.navigate(MainRoutes.Search(it))
                }
            )
        }

        composable<MainRoutes.Search> {
            SearchScreen(
                onBackPressed = navHostController::popBackStack
            )
        }
    }
}