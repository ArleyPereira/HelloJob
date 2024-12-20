package br.com.hellodev.setup.presenter.features.profile.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.setup.presenter.features.profile.action.ProfileAction
import br.com.hellodev.setup.presenter.features.profile.state.ProfileState
import br.com.hellodev.setup.presenter.features.profile.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileContent(
        state = state,
        action = viewModel::dispatchAction,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun ProfileContent(
    state: ProfileState,
    action: (ProfileAction) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {

        },
        containerColor = HelloTheme.colorScheme.screen.background,
        content = { paddingValues ->

        }
    )
}

@Preview
@Composable
private fun ProfilePreview() {
    HelloTheme {
        ProfileContent(
            state = ProfileState(),
            action = {},
            onBackPressed = {}
        )
    }
}