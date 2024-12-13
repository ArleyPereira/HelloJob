package br.com.hellodev.hellojob.presenter.features.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.hellojob.presenter.features.onboarding.state.WelcomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WelcomeViewModel() : ViewModel() {

    private var _state = MutableStateFlow(WelcomeUiState())
    var state: StateFlow<WelcomeUiState> = _state

    init {

    }

//    fun dispatchAction(action: WelcomeAction) {
//        when (action) {
//
//        }
//    }

    private fun getSlideItems() {
        val slideItems = listOf(
            Pair(
                first = "Bem-vindo",
                second = "O melhor aplicativo de streaming de filmes do século para tornar seus dias incríveis!"
            ),
            Pair(
                first = "Bem-vindo",
                second = "O melhor aplicativo de streaming de filmes do século para tornar seus dias incríveis!"
            ),
            Pair(
                first = "Bem-vindo",
                second = "O melhor aplicativo de streaming de filmes do século para tornar seus dias incríveis!"
            )
        )

        _state.value = _state.value.copy(
            slideItems = slideItems
        )
    }

}