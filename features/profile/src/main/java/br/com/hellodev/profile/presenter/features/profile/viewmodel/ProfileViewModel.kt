package br.com.hellodev.profile.presenter.features.profile.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.core.enums.input.InputType.COUNTRY
import br.com.hellodev.core.enums.input.InputType.DATE_BIRTH
import br.com.hellodev.core.enums.input.InputType.FIRST_NAME
import br.com.hellodev.core.enums.input.InputType.GENRE
import br.com.hellodev.core.enums.input.InputType.SURNAME
import br.com.hellodev.core.functions.capitalizeEachWord
import br.com.hellodev.core.functions.isValidBirthDate
import br.com.hellodev.core.functions.isValidName
import br.com.hellodev.profile.presenter.features.profile.action.ProfileAction
import br.com.hellodev.profile.presenter.features.profile.state.ProfileState
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

            is ProfileAction.OnChangeImage -> {
                onChangeImage(uri = action.uri)
            }
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    name = "Arley",
                    surname = "Santana",
                    dateBirth = "28041995",
                    email = "arley@gmail.com",
                    phone = "27996375733",
                    genres = getGenres(),
                    isLoading = false
                )
            }
        }
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
        val genre = _state.value.genre != null
        val country = _state.value.country != null

        return name && surname && dateBirth && genre && country
    }

    private fun getGenres(): List<String> {
        return listOf("Masculino", "Feminino", "Outro")
    }

    private fun clearError() {
        _state.update { currentState ->
            currentState.copy(inputError = null)
        }
    }

}