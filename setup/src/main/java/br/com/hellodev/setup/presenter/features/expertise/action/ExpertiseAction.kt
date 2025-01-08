package br.com.hellodev.setup.presenter.features.expertise.action

import br.com.hellodev.setup.domain.model.expertise.Expertise

sealed class ExpertiseAction {
    data class OnSelect(val expertise: Expertise): ExpertiseAction()
}