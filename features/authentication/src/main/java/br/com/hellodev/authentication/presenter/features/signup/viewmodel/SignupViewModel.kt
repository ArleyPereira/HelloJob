package br.com.hellodev.authentication.presenter.features.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.authentication.presenter.features.signup.action.SignupAction
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.core.functions.isValidEmail
import br.com.hellodev.moviestreaming.presenter.features.authentication.signup.state.SignupState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun submitAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnValueChange -> {
                onValueChange(action.value, action.type)
            }

            is SignupAction.OnPasswordVisibilityChange -> {
                onPasswordVisibilityChange()
            }

            is SignupAction.OnSignup -> {
                onSignup()
            }

            is SignupAction.ResetError -> {
                resetError()
            }
        }
    }

    private fun onSignup() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(isAuthenticated = true)
            }
        }
    }

    private fun onValueChange(value: String, type: InputType) {
        when (type) {
            InputType.EMAIL -> {
                onEmailChange(value)
            }

            InputType.PASSWORD -> {
                onPasswordChange(value)
            }

            else -> {}
        }

        enabledSignupButton()
    }

    private fun onEmailChange(value: String) {
        _state.update { currentState ->
            currentState.copy(email = value)
        }
    }

    private fun onPasswordChange(value: String) {
        _state.update { currentState ->
            currentState.copy(password = value)
        }
    }

    private fun onPasswordVisibilityChange() {
        _state.update { currentState ->
            currentState.copy(passwordVisibility = !currentState.passwordVisibility)
        }
    }

    private fun enabledSignupButton() {
        val emailValid = isValidEmail(_state.value.email)
        val passwordValid = _state.value.password.isNotBlank()

        _state.update { currentState ->
            currentState.copy(enabledSignupButton = emailValid && passwordValid)
        }
    }

    private fun resetError() {
        _state.update { currentState ->
            currentState.copy(hasError = false, feedbackUI = null)
        }
    }

}