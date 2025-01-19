package br.com.hellodev.main.presenter.navigation.routes

import kotlinx.serialization.Serializable

sealed class MainRoutes {

    @Serializable
    data object Graph: MainRoutes()

    @Serializable
    data object Home: MainRoutes()

    @Serializable
    data object Saved: MainRoutes()

    @Serializable
    data object Application: MainRoutes()

}