package br.com.hellodev.setup.presenter.features.profile.action

import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.setup.presenter.features.profile.parameter.ProfileParameter

sealed class ProfileAction {
    data object Update : ProfileAction()
    data class OnTextFieldChanged(val value: String, val type: InputType) : ProfileAction()
    data class SetBackResult(val parameter: ProfileParameter) : ProfileAction()
}