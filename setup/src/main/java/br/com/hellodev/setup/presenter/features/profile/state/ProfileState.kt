package br.com.hellodev.setup.presenter.features.profile.state

import android.net.Uri
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.setup.domain.model.country.Country
import br.com.hellodev.setup.domain.model.genre.Genre

data class ProfileState(
    val isLoading: Boolean = true,
    val name: String = "Arley",
    val surname: String = "Santana",
    val dateBirth: String = "28041995",
    val email: String = "arley@gmail.com",
    val phone: String = "27996375733",
    val genre: Genre? = Genre.items[0],
    val country: Country? = Country.items[23],
    val inputError: InputType? = null,
    val selectedImageUri: Uri? = null,
    val navigateToHomeScreen: Boolean = false,
)
