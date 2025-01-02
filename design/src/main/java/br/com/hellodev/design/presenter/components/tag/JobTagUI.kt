package br.com.hellodev.design.presenter.components.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun JobTagUI(
    modifier: Modifier = Modifier,
    text: String
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(6.dp))
            .background(HelloTheme.colorScheme.tag.background)
            .border(
                width = 1.dp,
                color = HelloTheme.colorScheme.tag.border,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(
                    vertical = 6.dp,
                    horizontal = 10.dp
                ),
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(600),
                color = HelloTheme.colorScheme.tag.text,
                letterSpacing = 0.2.sp
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun JobTagUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HelloTheme.colorScheme.screen.backgroundSecondary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                JobTagUI(text = "Freenlance")

                JobTagUI(text = "Remoto")
            }
        }
    }
}