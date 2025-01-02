package br.com.hellodev.setup.presenter.features.profile.state

import android.net.Uri
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.setup.domain.model.country.Country
import br.com.hellodev.setup.domain.model.genre.Genre

data class ProfileState(
    val isLoading: Boolean = true,
    val name: String = "",
    val surname: String = "",
    val dateBirth: String = "",
    val email: String = "",
    val phone: String = "",
    val genre: Genre? = null,
    val country: Country? = null,
    val inputError: InputType? = null,
    val selectedImageUri: Uri? = null
)
