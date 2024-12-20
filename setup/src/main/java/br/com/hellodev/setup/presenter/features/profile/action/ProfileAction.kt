package br.com.hellodev.setup.presenter.features.profile.action

sealed class ProfileAction {
    data object Update : ProfileAction()
}