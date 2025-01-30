package br.com.hellodev.main.presenter.features.account.state

import android.net.Uri
import br.com.hellodev.common.domain.model.user.UserDomain

data class AccountState(
    val isLoading: Boolean = true,
    val isUploading: Boolean = false,
    val user: UserDomain? = null,
    val uriPdf: Uri? = null
)
