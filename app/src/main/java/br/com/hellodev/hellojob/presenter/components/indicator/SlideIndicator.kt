package br.com.hellodev.hellojob.presenter.components.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.hellojob.presenter.ui.theme.HelloTheme

@Composable
fun SlideIndicator(
    modifier: Modifier = Modifier,
    totalIndicators: Int,
    currentIndicator: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalIndicators) { index ->
            if (index == currentIndicator) {
                Spacer(
                    modifier = Modifier
                        .width(32.dp)
                        .height(8.dp)
                        .clip(CircleShape)
                        .background(HelloTheme.colorScheme.defaultColor)
                )
            } else {
                Spacer(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(HelloTheme.colorScheme.greyscale300Color)
                )
            }

            if (index != totalIndicators - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SlideIndicatorPreview() {
    HelloTheme(isDarkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HelloTheme.colorScheme.screen.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SlideIndicator(totalIndicators = 3, currentIndicator = 1)
        }
    }
}