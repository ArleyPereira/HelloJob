package br.com.hellodev.authentication.presenter.features.login.action

import br.com.hellodev.core.enums.input.InputType

sealed class LoginAction {

    data class OnValueChange(
        val value: String,
        val type: InputType
    ) : LoginAction()

    data object OnPasswordVisibilityChange : LoginAction()

    data object OnSignIn : LoginAction()

    data object ResetError : LoginAction()

}