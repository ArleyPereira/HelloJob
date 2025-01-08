package br.com.hellodev.setup.presenter.features.genre.action

import br.com.hellodev.setup.domain.model.genre.Genre

sealed class GenreAction {
    data class OnGenreSelected(val genre: Genre) : GenreAction()
}