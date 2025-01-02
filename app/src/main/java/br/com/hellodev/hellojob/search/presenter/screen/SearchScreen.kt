package br.com.hellodev.hellojob.search.presenter.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.common.domain.model.job.item.JobItemDomain
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.bar.search.SearchBarUI
import br.com.hellodev.design.presenter.components.empty.EmptyUI
import br.com.hellodev.design.presenter.components.item.job.item.JobItemUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.hellojob.search.presenter.action.SearchAction
import br.com.hellodev.hellojob.search.presenter.state.SearchState
import br.com.hellodev.hellojob.search.presenter.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<SearchViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchContent(
        state = state,
        action = viewModel::dispatchAction,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun SearchContent(
    state: SearchState,
    action: (SearchAction) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {

        },
        containerColor = HelloTheme.colorScheme.screen.backgroundPrimary,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                SearchBarUI(
                    modifier = Modifier
                        .padding(24.dp),
                    value = state.query,
                    placeholder = "Pesquise vaga ou empresa",
                    trailingIcon = {
                        IconButton(
                            onClick = {},
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_filter),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        )
                    },
                    onBackPressed = onBackPressed,
                    onValueChange = {
                        action(SearchAction.OnSearchChange(it))
                    },
                    onSearchAction = {
                        action(SearchAction.OnSearch)
                    }
                )

                when {
                    state.isLoading -> {

                    }

                    0 == 1 -> {
                        EmptyUI(
                            modifier = Modifier
                                .fillMaxSize(),
                            title = "Não encontrado",
                            description = "Desculpe, a palavra-chave que você digitou não pode ser encontrada. Verifique novamente ou pesquise com outra palavra-chave."
                        )
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentPadding = PaddingValues(bottom = 24.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            items(
                                items = state.jobs,
                                key = { it.id ?: 0 }
                            ) { job ->
                                JobItemUI(
                                    modifier = Modifier
                                        .padding(horizontal = 24.dp),
                                    job = job
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun SearchPreview() {
    HelloTheme {
        SearchContent(
            state = SearchState(
                jobs = JobItemDomain.items
            ),
            action = {},
            onBackPressed = {}
        )
    }
}