package br.com.hellodev.setup.presenter.features.profile.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.setup.presenter.features.profile.action.ProfileAction
import br.com.hellodev.setup.presenter.features.profile.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel: ViewModel() {

    private var _state = MutableStateFlow(ProfileState())
    var state: StateFlow<ProfileState> = _state

    init {
        getProfile()
    }

    fun dispatchAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.Update -> {
                update()
            }
        }
    }

    private fun getProfile() {

    }

    private fun update() {

    }

}