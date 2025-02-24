package br.com.hellodev.setup.presenter.features.expertise.state

import br.com.hellodev.common.domain.model.expertise.Expertise

data class ExpertiseState(
    val isLoading: Boolean = true,
    val expertises: List<br.com.hellodev.common.domain.model.expertise.Expertise> = emptyList(),
    val selectedExpertises: List<br.com.hellodev.common.domain.model.expertise.Expertise> = emptyList()
)
