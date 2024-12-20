package br.com.hellodev.authentication.presenter.features.login.state

import br.com.hellodev.core.enums.feedback.FeedbackType

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "u1@gmail.com",
    val password: String = "teste",
    val passwordVisibility: Boolean = false,
    val enabledSignInButton: Boolean = true,
    val hasFeedback: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null,
    val isAuthenticated: Boolean = false
)
