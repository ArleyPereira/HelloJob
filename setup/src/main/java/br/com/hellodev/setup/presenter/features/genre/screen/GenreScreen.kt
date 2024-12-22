package br.com.hellodev.setup.presenter.features.genre.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.radio.RadioButtonUi
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.setup.R
import br.com.hellodev.setup.domain.model.genre.Genre
import br.com.hellodev.setup.presenter.features.genre.action.GenreAction
import br.com.hellodev.setup.presenter.features.genre.state.GenreState
import br.com.hellodev.setup.presenter.features.genre.viewmodel.GenreViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GenreScreen(
    onBackPressed: () -> Unit
) {

    val viewModel = koinViewModel<GenreViewModel>()
    val state by viewModel.state.collectAsState()

    GenreContent(
        state = state,
        action = viewModel::submitAction,
        onBackPressed = onBackPressed
    )

}

@Composable
fun GenreContent(
    state: GenreState,
    action: (GenreAction) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = stringResource(R.string.text_title_genre_screen),
                onClick = onBackPressed
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .background(HelloTheme.colorScheme.screen.background)
            ) {
                HorizontalDividerUI()

                PrimaryButton(
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 24.dp,
                            bottom = 32.dp
                        ),
                    text = stringResource(R.string.text_button_select_genre_screen),
                    enabled = state.selectedGenre != null,
                    onClick = { }
                )
            }
        },
        containerColor = HelloTheme.colorScheme.screen.background,
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    bottom = 24.dp,
                    top = 8.dp
                )
            ) {
                items(state.genres) { genre ->
                    RadioButtonUi(
                        modifier = Modifier
                            .fillMaxWidth(),
                        selected = genre == state.selectedGenre,
                        text = genre.name ?: "",
                        onClick = {
                            action(GenreAction.OnGenreSelected(genre))
                        }
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun GenrePreview() {
    HelloTheme {
        GenreContent(
            state = GenreState(
                genres = Genre.genres
            ),
            action = {},
            onBackPressed = {}
        )
    }
}