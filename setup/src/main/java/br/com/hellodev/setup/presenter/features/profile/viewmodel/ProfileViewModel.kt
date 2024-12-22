package br.com.hellodev.setup.presenter.features.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.core.enums.input.InputType.*
import br.com.hellodev.core.functions.capitalizeFirstLetter
import br.com.hellodev.core.functions.isValidBirthDate
import br.com.hellodev.core.functions.isValidEmail
import br.com.hellodev.core.functions.isValidName
import br.com.hellodev.core.functions.isValidPhone
import br.com.hellodev.setup.presenter.features.profile.action.ProfileAction
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

            // Envia os dados do perfil para algum lugar
        }
    }

    private fun onTextFieldChanged(value: String, type: InputType) {
        _state.update { currentState ->
            currentState.copy(inputError = null)
        }

        _state.update { currentState ->
            when (type) {
                FIRST_NAME -> currentState.copy(name = capitalizeFirstLetter(value))
                SURNAME -> currentState.copy(surname = capitalizeFirstLetter(value))
                EMAIL -> currentState.copy(email = value.lowercase())
                PHONE -> currentState.copy(phone = value)
                GENRE -> currentState.copy(genre = value)
                COUNTRY -> currentState.copy(country = value)
                DATE_BIRTH -> currentState.copy(dateBirth = value)
                else -> currentState
            }
        }
    }

    private fun inputFeedbackError() {
        val inputError = when {
            !isValidName(_state.value.name) -> FIRST_NAME
            !isValidName(_state.value.surname) -> SURNAME
            !isValidBirthDate(_state.value.dateBirth) -> DATE_BIRTH
            !isValidEmail(_state.value.email) -> EMAIL
            !isValidPhone(_state.value.phone) -> PHONE
            _state.value.genre.isEmpty() -> GENRE
            _state.value.country.isEmpty() -> COUNTRY
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
        val genre = _state.value.genre.isNotEmpty()
        val country = _state.value.country.isNotEmpty()

        return name && surname && phone && dateBirth && email && genre && country
    }

}