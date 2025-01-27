package br.com.hellodev.setup.presenter.features.profile.action

import android.net.Uri
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.setup.presenter.features.profile.parameter.SetupProfileParameter

sealed class SetupProfileAction {
    data object Update : SetupProfileAction()
    data class OnTextFieldChanged(val value: String, val type: InputType) : SetupProfileAction()
    data class SetBackResult(val parameter: SetupProfileParameter) : SetupProfileAction()
    data class OnChangeImage(val uri: Uri?) : SetupProfileAction()
}