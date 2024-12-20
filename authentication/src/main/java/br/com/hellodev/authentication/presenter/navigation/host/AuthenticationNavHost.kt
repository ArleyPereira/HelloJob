package br.com.hellodev.authentication.presenter.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.authentication.presenter.navigation.routes.AuthenticationRoutes
import br.com.hellodev.authentication.presenter.features.home.HomeAuthenticationScreen
import br.com.hellodev.authentication.presenter.features.login.screen.LoginScreen
import br.com.hellodev.authentication.presenter.features.signup.screen.SignupScreen
import br.com.hellodev.setup.presenter.navigation.host.setupNavHost
import br.com.hellodev.setup.presenter.navigation.routes.SetupRoutes

fun NavGraphBuilder.authenticationNavHost(navHostController: NavHostController) {
    navigation<AuthenticationRoutes.Graph>(
        startDestination = AuthenticationRoutes.Home
    ) {
        composable<AuthenticationRoutes.Home> {
            HomeAuthenticationScreen(
                navigateToLoginScreen = {
                    navHostController.navigate(AuthenticationRoutes.Login)
                },
                navigateToSignupScreen = {
                    navHostController.navigate(AuthenticationRoutes.Signup)
                }
            )
        }

        composable<AuthenticationRoutes.Login> {
            LoginScreen(
                navigateToAppScreen = {
                    navHostController.navigate(SetupRoutes.Graph) {
                        popUpTo(AuthenticationRoutes.Graph) {
                            inclusive = true
                        }
                    }
                },
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        composable<AuthenticationRoutes.Signup> {
            SignupScreen(
                navigateToAppScreen = {
                    navHostController.navigate(SetupRoutes.Graph) {
                        popUpTo(AuthenticationRoutes.Graph) {
                            inclusive = true
                        }
                    }
                },
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        setupNavHost(navHostController = navHostController)
    }
}