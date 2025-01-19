package br.com.hellodev.design.presenter.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.presenter.theme.HelloTheme

@Composable
fun CircularProgressLoading(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = HelloTheme.colorScheme.defaultColor
    )
}

@PreviewLightDark
@Composable
private fun CircularProgressLoadingPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .background(HelloTheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressLoading(
                modifier = Modifier
                    .padding(32.dp)
            )
        }
    }
}