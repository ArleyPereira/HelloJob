package br.com.hellodev.hellojob.search.presenter.action

sealed class SearchAction {
    data object OnSearch : SearchAction()
    data class OnSearchChange(val query: String) : SearchAction()
}