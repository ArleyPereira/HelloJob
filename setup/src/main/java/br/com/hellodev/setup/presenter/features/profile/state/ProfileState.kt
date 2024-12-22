package br.com.hellodev.setup.presenter.features.profile.state

import br.com.hellodev.core.enums.input.InputType

data class ProfileState(
    val isLoading: Boolean = true,
    val name: String = "",
    val surname: String = "",
    val dateBirth: String = "",
    val email: String = "",
    val phone: String = "",
    val genre: String = "",
    val country: String = "",
    val inputError: InputType? = null
)
