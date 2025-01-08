package br.com.hellodev.main.presenter.features.search.state

import br.com.hellodev.common.domain.model.job.item.JobItemDomain

data class SearchState(
    val isLoading: Boolean = true,
    val query: String = "",
    val jobs: List<JobItemDomain> = emptyList()
)
