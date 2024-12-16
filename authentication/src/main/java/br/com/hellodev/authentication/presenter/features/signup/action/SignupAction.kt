package br.com.hellodev.authentication.presenter.features.signup.action

import br.com.hellodev.design.core.enums.input.InputType

sealed class SignupAction {

    data class OnValueChange(
        val value: String,
        val type: InputType
    ) : SignupAction()

    data object OnPasswordVisibilityChange : SignupAction()

    data object OnSignup : SignupAction()

    data object ResetError : SignupAction()

}