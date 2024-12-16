package br.com.hellodev.authentication.core.navigation.routes

import kotlinx.serialization.Serializable

sealed class AuthenticationRoutes {

    @Serializable
    data object Graph : AuthenticationRoutes()

    @Serializable
    data object Home : AuthenticationRoutes()

    @Serializable
    data object Login : AuthenticationRoutes()

    @Serializable
    data object Signup : AuthenticationRoutes()

}