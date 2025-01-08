package br.com.hellodev.moviestreaming.presenter.features.authentication.signup.state

import br.com.hellodev.core.enums.feedback.FeedbackType

data class SignupState(
    val isLoading: Boolean = false,
    val email: String = "u@gmail.com",
    val password: String = "teste123",
    val passwordVisibility: Boolean = false,
    val enabledSignupButton: Boolean = false,
    val hasError: Boolean = false,
    val feedbackUI: Pair<br.com.hellodev.core.enums.feedback.FeedbackType, Int>? = null,
    val isAuthenticated: Boolean = false
)
