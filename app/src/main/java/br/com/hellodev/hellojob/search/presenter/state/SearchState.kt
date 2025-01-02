package br.com.hellodev.hellojob.search.presenter.state

import br.com.hellodev.common.domain.model.job.item.JobItemDomain

data class SearchState(
    val isLoading: Boolean = false,
    val query: String = "",
    val jobs: List<JobItemDomain> = emptyList()
)
