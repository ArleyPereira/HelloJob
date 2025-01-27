package br.com.hellodev.main.presenter.features.account.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.main.presenter.features.account.action.AccountAction
import br.com.hellodev.main.presenter.features.account.state.AccountState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel(

): ViewModel() {

    private var _state = MutableStateFlow(AccountState())
    var state: StateFlow<AccountState> = _state

    init {

    }

    fun dispatchAction(action: AccountAction) {
        when (action) {
            is AccountAction.OnLogout -> {
                onLogout()
            }

            is AccountAction.OnSelectedCurriculum -> {
                onSelectedCurriculum(uri = action.uri)
            }

            is AccountAction.OnDeleteCurriculum -> {
                onDeleteCurriculum()
            }
        }
    }

    private fun onSelectedCurriculum(uri: Uri) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(isUploading = true)
            }

            delay(2000)

            _state.update { currentState ->
                currentState.copy(
                    uriPdf = uri,
                    isUploading = false
                )
            }
        }
    }

    private fun onDeleteCurriculum() {
        _state.update { currentState ->
            currentState.copy(uriPdf = null)
        }
    }

    private fun onLogout() {

    }

}