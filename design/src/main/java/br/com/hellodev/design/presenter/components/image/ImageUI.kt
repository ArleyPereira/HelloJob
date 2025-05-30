package br.com.hellodev.design.presenter.components.image

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.loading.CircularProgressLoading
import br.com.hellodev.design.presenter.theme.HelloTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun ImageUI(
    modifier: Modifier = Modifier,
    imageModel: Any? = null,
    contentScale: ContentScale = ContentScale.None,
    previewPlaceholder: Painter? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    isLoading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val failureComposition by rememberLottieComposition(LottieCompositionSpec.Asset("error_loading_error.json"))

    CoilImage(
        imageModel = { imageModel },
        modifier = modifier
            .clip(shape)
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .border(borderStroke, shape),
        imageOptions = ImageOptions(
            contentScale = contentScale,
            alignment = Alignment.Center
        ),
        previewPlaceholder = previewPlaceholder,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressLoading()
            }
        },
        failure = {
            if (!isLoading) {
                LottieAnimation(
                    composition = failureComposition,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter),
                    iterations = LottieConstants.IterateForever,
                    maintainOriginalImageBounds = true
                )
            }
        }
    )
}

@Preview
@Composable
private fun ImageUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HelloTheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageUI(
                modifier = Modifier
                    .size(200.dp),
                imageModel = R.drawable.logo,
                contentScale = ContentScale.Crop,
                previewPlaceholder = painterResource(id = R.drawable.logo),
                borderStroke = BorderStroke(2.dp, HelloTheme.colorScheme.defaultColor),
                onClick = {}
            )
        }
    }
}