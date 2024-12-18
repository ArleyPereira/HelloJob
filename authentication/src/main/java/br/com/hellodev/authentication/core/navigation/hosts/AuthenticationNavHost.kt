package br.com.hellodev.authentication.core.navigation.hosts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.authentication.core.navigation.routes.AuthenticationRoutes
import br.com.hellodev.authentication.presenter.features.home.HomeAuthenticationScreen
import br.com.hellodev.authentication.presenter.features.login.screen.LoginScreen
import br.com.hellodev.authentication.presenter.features.signup.screen.SignupScreen

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

                },
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        composable<AuthenticationRoutes.Signup> {
            SignupScreen(
                navigateToAppScreen = {

                },
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}