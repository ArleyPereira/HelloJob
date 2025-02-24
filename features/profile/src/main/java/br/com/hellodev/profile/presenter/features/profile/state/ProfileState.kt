package br.com.hellodev.profile.presenter.features.profile.state

import android.net.Uri
import br.com.hellodev.common.domain.model.country.Country
import br.com.hellodev.core.enums.input.InputType

data class ProfileState(
    val isLoading: Boolean = true,
    val name: String = "",
    val surname: String = "",
    val dateBirth: String = "",
    val email: String = "",
    val phone: String = "",
    val genre: String? = null,
    val country: Country? = Country.items[23],
    val inputError: InputType? = null,
    val selectedImageUri: Uri? = null,
    val navigateToHomeScreen: Boolean = false,
    val genres: List<String> = emptyList()
)
