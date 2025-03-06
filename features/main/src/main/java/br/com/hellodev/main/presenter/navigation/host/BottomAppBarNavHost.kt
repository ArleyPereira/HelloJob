package br.com.hellodev.main.presenter.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.hellodev.core.extensions.popBackStackSafely
import br.com.hellodev.job_details.presenter.navigation.host.detailsNavHost
import br.com.hellodev.job_details.presenter.navigation.routes.DetailsRoutes
import br.com.hellodev.job_search.presenter.navigation.host.searchNavHost
import br.com.hellodev.job_search.presenter.navigation.routes.SearchRoutes
import br.com.hellodev.main.presenter.features.applications.list.screen.ApplicationListScreen
import br.com.hellodev.main.presenter.features.applications.status.screen.ApplicationStatusScreen
import br.com.hellodev.main.presenter.features.contact.screen.ContactInformationScreen
import br.com.hellodev.main.presenter.features.main.account.screen.AccountScreen
import br.com.hellodev.main.presenter.features.main.home.screen.HomeScreen
import br.com.hellodev.main.presenter.features.salary.screen.SalaryExpectationScreen
import br.com.hellodev.main.presenter.features.saved.screen.SavedScreen
import br.com.hellodev.main.presenter.features.summary.screen.SummaryScreen
import br.com.hellodev.main.presenter.navigation.routes.BottomBarRoutes
import br.com.hellodev.profile.presenter.features.profile.screen.ProfileScreen

@Composable
fun BottomAppBarNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarRoutes.Home,
        modifier = modifier
    ) {
        composable<BottomBarRoutes.Home> {
            HomeScreen(
                navigateToDetails = {
                    navHostController.navigate(DetailsRoutes.Details(id = it))
                },
                navigateToSearchScreen = {
                    navHostController.navigate(SearchRoutes.Search(it))
                }
            )
        }

        composable<BottomBarRoutes.Saved> {
            SavedScreen(
                navigateToDetails = {
                    navHostController.navigate(DetailsRoutes.Details(id = it))
                }
            )
        }

        composable<BottomBarRoutes.ApplicationList> {
            ApplicationListScreen(
                navigateToApplicationStatusScreen = {
                    navHostController.navigate(BottomBarRoutes.ApplicationStatus(id = 1))
                }
            )
        }

        composable<BottomBarRoutes.ApplicationStatus> {
            ApplicationStatusScreen(
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        composable<BottomBarRoutes.Message> {

        }

        composable<BottomBarRoutes.Account> {
            AccountScreen(
                navigateToProfileScreen = {
                    navHostController.navigate(BottomBarRoutes.Profile)
                },
                navigateToContactInformationScreen = {
                    navHostController.navigate(BottomBarRoutes.ContactInformation)
                },
                navigateToSummaryScreen = {
                    navHostController.navigate(BottomBarRoutes.Summary)
                },
                navigateToSalaryExpectationScreen = {
                    navHostController.navigate(BottomBarRoutes.SalaryExpectation)
                }
            )
        }

        composable<BottomBarRoutes.Profile> {
            ProfileScreen(
                navigateToCountryScreen = {

                },
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        composable<BottomBarRoutes.Summary> {
            SummaryScreen(
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        composable<BottomBarRoutes.SalaryExpectation> {
            SalaryExpectationScreen(
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        composable<BottomBarRoutes.ContactInformation> {
            ContactInformationScreen(
                onBackPressed = navHostController::popBackStackSafely
            )
        }

        detailsNavHost(navHostController = navHostController)

        searchNavHost(navHostController = navHostController)
    }
}