package br.com.hellodev.main.presenter.features.search.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.common.domain.model.job.item.JobItemDomain
import br.com.hellodev.core.enums.icon.IconType
import br.com.hellodev.core.enums.icon.IconType.*
import br.com.hellodev.main.R
import br.com.hellodev.design.presenter.components.bar.search.SearchBarUI
import br.com.hellodev.design.presenter.components.empty.EmptyUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.item.job.item.JobItemUI
import br.com.hellodev.design.presenter.components.loading.CircularLoadingScreen
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.main.presenter.features.search.action.SearchAction
import br.com.hellodev.main.presenter.features.search.state.SearchState
import br.com.hellodev.main.presenter.features.search.viewmodel.SearchViewModel
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 24.dp,
                        start = 24.dp,
                        end = 24.dp
                    )
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                SearchBarUI(
                    modifier = Modifier,
                    value = state.query,
                    placeholder = "Pesquise vaga ou empresa",
                    trailingIcon = {
                        IconButton(
                            onClick = {},
                            content = {
                                DefaultIcon(type = IC_FILTER)
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
                        CircularLoadingScreen()
                    }

                    else -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${state.jobs.size} encontrado",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    lineHeight = 24.sp,
                                    fontFamily = UrbanistFamily,
                                    fontWeight = FontWeight(700),
                                    color = HelloTheme.colorScheme.text.color
                                )
                            )

                            DefaultIcon(
                                type = IC_CLASSIFY,
                                tint = HelloTheme.colorScheme.defaultColor,
                                onClick = {}
                            )
                        }

                        if (state.jobs.isEmpty()) {
                            EmptyUI(
                                modifier = Modifier
                                    .fillMaxSize(),
                                title = "Não encontrado",
                                description = "Desculpe, a palavra-chave que você digitou não pode ser encontrada. Verifique novamente ou pesquise com outra palavra-chave."
                            )
                        }

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentPadding = PaddingValues(
                                bottom = WindowInsets.systemBars.asPaddingValues()
                                    .calculateBottomPadding() + 32.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            items(
                                items = state.jobs,
                                key = { it.id ?: 0 }
                            ) { job ->
                                JobItemUI(
                                    job = job,
                                    onSaveClick = {},
                                    onClick = {}
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
                isLoading = false,
                jobs = listOf()//JobItemDomain.items
            ),
            action = {},
            onBackPressed = {}
        )
    }
}