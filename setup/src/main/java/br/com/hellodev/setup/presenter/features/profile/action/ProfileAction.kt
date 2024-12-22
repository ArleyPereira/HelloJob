package br.com.hellodev.setup.presenter.features.profile.action

import br.com.hellodev.core.enums.input.InputType

sealed class ProfileAction {
    data object Update : ProfileAction()
    data class OnTextFieldChanged(val value: String, val type: InputType) : ProfileAction()
}