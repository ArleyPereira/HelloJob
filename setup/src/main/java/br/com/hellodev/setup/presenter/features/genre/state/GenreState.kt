package br.com.hellodev.setup.presenter.features.genre.state

import br.com.hellodev.setup.domain.model.genre.Genre

data class GenreState(
    val selectedGenre: Genre? = null,
    val genres: List<Genre> = emptyList(),
)
