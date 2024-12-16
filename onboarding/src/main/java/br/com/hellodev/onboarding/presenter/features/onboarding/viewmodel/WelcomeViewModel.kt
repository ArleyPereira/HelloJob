package br.com.hellodev.onboarding.presenter.features.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.design.model.slider.SliderItem
import br.com.hellodev.onboarding.presenter.features.onboarding.state.WelcomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WelcomeViewModel() : ViewModel() {

    private var _state = MutableStateFlow(WelcomeUiState())
    var state: StateFlow<WelcomeUiState> = _state

    init {
        getSlideItems()
    }

//    fun dispatchAction(action: WelcomeAction) {
//        when (action) {
//
//        }
//    }

    private fun getSlideItems() {
        _state.update {
            it.copy(slideItems = SliderItem.getSlideItems)
        }
    }

}