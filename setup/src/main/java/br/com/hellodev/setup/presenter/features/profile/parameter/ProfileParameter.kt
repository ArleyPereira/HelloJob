package br.com.hellodev.setup.presenter.features.profile.parameter

import br.com.hellodev.setup.domain.model.country.Country
import br.com.hellodev.setup.domain.model.genre.Genre
import kotlinx.serialization.Serializable

@Serializable
data class ProfileParameter(
    val genre: Genre? = null,
    val country: Country? = null
)
