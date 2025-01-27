package br.com.hellodev.main.presenter.features.account.state

import android.net.Uri

data class AccountState(
    val isLoading: Boolean = true,
    val isUploading: Boolean = false,
    val uriPdf: Uri? = null
)
