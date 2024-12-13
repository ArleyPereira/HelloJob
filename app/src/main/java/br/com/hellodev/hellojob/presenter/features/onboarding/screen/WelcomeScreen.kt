package br.com.hellodev.hellojob.presenter.features.onboarding.screen

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.hellojob.R
import br.com.hellodev.hellojob.presenter.components.button.PrimaryButton
import br.com.hellodev.hellojob.presenter.components.slide.WelcomeSlideUI
import br.com.hellodev.hellojob.presenter.ui.theme.HelloTheme
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier
) {
    val activity = LocalActivity.current

    WelcomeContent(
        modifier = modifier,
        onBackPressed = {
            activity?.finish()
        }
    )
}

@Composable
private fun WelcomeContent(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    BackHandler { onBackPressed() }

    val scope = rememberCoroutineScope()
    val slideItems = listOf(
        Pair(
            first = "Descubra Oportunidades",
            second = "Encontre vagas que combinam com suas habilidades e interesses."
        ),
        Pair(
            first = "Perfil Personalizado",
            second = "Crie um perfil profissional para atrair os melhores recrutadores."
        ),
        Pair(
            first = "Alerta de Vagas",
            second = "Receba notificações de vagas que combinam com seu perfil."
        )
    )
    val pagerState = rememberPagerState {
        slideItems.size
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(HelloTheme.colorScheme.screen.background),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeSlideUI(
            modifier = Modifier
                .weight(1f),
            slideItems = slideItems,
            pagerState = pagerState
        )

        PrimaryButton(
            text = stringResource(R.string.label_continue_button_welcome_screen),
            modifier = Modifier
                .padding(
                    bottom = 32.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            onClick = {
                scope.launch {
                    val nextPage = pagerState.currentPage + 1
                    if (nextPage < slideItems.size) {
                        pagerState.animateScrollToPage(nextPage)
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun WelcomePreview() {
    HelloTheme(isDarkTheme = false) {
        WelcomeContent(
            onBackPressed = {}
        )
    }
}