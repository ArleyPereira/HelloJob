package br.com.hellodev.setup.presenter.features.profile.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.core.enums.input.InputType.COUNTRY
import br.com.hellodev.core.enums.input.InputType.DATE_BIRTH
import br.com.hellodev.core.enums.input.InputType.EMAIL
import br.com.hellodev.core.enums.input.InputType.FIRST_NAME
import br.com.hellodev.core.enums.input.InputType.GENRE
import br.com.hellodev.core.enums.input.InputType.PHONE
import br.com.hellodev.core.enums.input.InputType.SURNAME
import br.com.hellodev.core.functions.capitalizeEachWord
import br.com.hellodev.core.functions.isValidBirthDate
import br.com.hellodev.core.functions.isValidEmail
import br.com.hellodev.core.functions.isValidName
import br.com.hellodev.core.functions.isValidPhone
import br.com.hellodev.setup.presenter.features.profile.action.ProfileAction
import br.com.hellodev.setup.presenter.features.profile.parameter.ProfileParameter
import br.com.hellodev.setup.presenter.features.profile.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private var _state = MutableStateFlow(ProfileState())
    var state: StateFlow<ProfileState> = _state

    init {
        getProfile()
    }

    fun dispatchAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.Update -> {
                update()
            }

            is ProfileAction.OnTextFieldChanged -> {
                onTextFieldChanged(value = action.value, type = action.type)
            }

            is ProfileAction.SetBackResult -> {
                setBackResult(parameter = action.parameter)
            }

            is ProfileAction.OnChangeImage -> {
                onChangeImage(uri = action.uri)
            }
        }
    }

    private fun getProfile() {

    }

    private fun update() {
        viewModelScope.launch {
            if (!isValidProfile()) {
                inputFeedbackError()
                return@launch
            }

            _state.update { currentState ->
                currentState.copy(navigateToHomeScreen = true)
            }

            // Envia os dados do perfil para algum lugar
        }
    }

    private fun onTextFieldChanged(value: String, type: InputType) {
        clearError()

        _state.update { currentState ->
            when (type) {
                FIRST_NAME -> currentState.copy(name = capitalizeEachWord(value))
                SURNAME -> currentState.copy(surname = capitalizeEachWord(value))
                EMAIL -> currentState.copy(email = value.lowercase())
                PHONE -> currentState.copy(phone = value)
                DATE_BIRTH -> currentState.copy(dateBirth = value)
                else -> currentState
            }
        }
    }

    private fun onChangeImage(uri: Uri?) {
        _state.update { currentState ->
            currentState.copy(selectedImageUri = uri)
        }
    }

    private fun inputFeedbackError() {
        val inputError = when {
            !isValidName(_state.value.name) -> FIRST_NAME
            !isValidName(_state.value.surname) -> SURNAME
            !isValidBirthDate(_state.value.dateBirth) -> DATE_BIRTH
            !isValidEmail(_state.value.email) -> EMAIL
            !isValidPhone(_state.value.phone) -> PHONE
            _state.value.genre == null -> GENRE
            _state.value.country == null -> COUNTRY
            else -> null
        }

        _state.update { currentState ->
            currentState.copy(inputError = inputError)
        }
    }

    private fun isValidProfile(): Boolean {
        val name = isValidName(_state.value.name)
        val surname = isValidName(_state.value.surname)
        val dateBirth = isValidBirthDate(_state.value.dateBirth)
        val email = isValidEmail(_state.value.email)
        val phone = isValidPhone(_state.value.phone)
        val genre = _state.value.genre != null
        val country = _state.value.country != null

        return name && surname && phone && dateBirth && email && genre && country
    }

    private fun setBackResult(parameter: ProfileParameter) {
        viewModelScope.launch {
            parameter.genre?.let {
                _state.update { currentState ->
                    currentState.copy(genre = it)
                }
            }

            parameter.country?.let {
                _state.update { currentState ->
                    currentState.copy(country = it)
                }
            }

            clearError()
        }
    }

    private fun clearError() {
        _state.update { currentState ->
            currentState.copy(inputError = null)
        }
    }

}