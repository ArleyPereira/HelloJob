package br.com.hellodev.authentication.presenter.features.signup.action

import br.com.hellodev.core.enums.input.InputType

sealed class SignupAction {

    data class OnValueChange(
        val value: String,
        val type: br.com.hellodev.core.enums.input.InputType
    ) : SignupAction()

    data object OnPasswordVisibilityChange : SignupAction()

    data object OnSignup : SignupAction()

    data object ResetError : SignupAction()

}