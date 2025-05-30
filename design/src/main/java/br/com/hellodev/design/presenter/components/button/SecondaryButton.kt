package br.com.hellodev.design.presenter.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("button_loading.json"))

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp),
        enabled = enabled && !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = HelloTheme.colorScheme.button.secondaryBackground,
            disabledContainerColor = HelloTheme.colorScheme.disabledDefaultColor
        ),
        content = {
            if (isLoading) {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier
                        .size(70.dp),
                    iterations = LottieConstants.IterateForever,
                    maintainOriginalImageBounds = true,
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight.Bold,
                        color = HelloTheme.colorScheme.button.secondaryText,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp
                    )
                )
            }

        }
    )
}

@PreviewLightDark
@Composable
private fun SecondaryButtonPreview() {
    HelloTheme {
        SecondaryButton(
            text = "Continuar",
            isLoading = false,
            enabled = true,
            onClick = {}
        )
    }
}