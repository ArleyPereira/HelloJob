package br.com.hellodev.authentication.presenter.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.authentication.presenter.features.login.action.LoginAction
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.authentication.presenter.features.login.state.LoginState
import br.com.hellodev.core.functions.isValidEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun submitAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnValueChange -> {
                onValueChange(action.value, action.type)
            }

            is LoginAction.OnSignIn -> {
                onSignIn()
            }

            is LoginAction.ResetError -> {
                resetError()
            }
        }
    }

    private fun onSignIn() {
        viewModelScope.launch {

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
        }

        enabledSignInButton()
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

    private fun enabledSignInButton() {
        val emailValid = isValidEmail(_state.value.email)
        val passwordValid = _state.value.password.isNotBlank()

        _state.update { currentState ->
            currentState.copy(enabledSignInButton = emailValid && passwordValid)
        }
    }

    private fun resetError() {
        _state.update { currentState ->
            currentState.copy(hasFeedback = false, feedbackUI = null)
        }
    }

}