package br.com.hellodev.hellojob.presenter.components.slide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.hellojob.R
import br.com.hellodev.hellojob.presenter.components.indicator.SlideIndicator
import br.com.hellodev.hellojob.presenter.ui.theme.HelloTheme
import br.com.hellodev.hellojob.presenter.ui.theme.UrbanistFamily

@Composable
fun WelcomeSlideUI(
    modifier: Modifier = Modifier,
    slideItems: List<Pair<String, String>>,
    pagerState: PagerState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(HelloTheme.colorScheme.screen.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            state = pagerState,
            Modifier
                .fillMaxSize()
                .weight(1f),
            pageContent = {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ImageSlider(pagerState)

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = slideItems[pagerState.currentPage].first,
                        style = TextStyle(
                            fontSize = 32.sp,
                            lineHeight = 48.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.Bold,
                            color = HelloTheme.colorScheme.defaultColor,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = slideItems[pagerState.currentPage].second,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 25.2.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.Medium,
                            color = HelloTheme.colorScheme.text.color,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.2.sp
                        )
                    )
                }
            }
        )

        SlideIndicator(
            modifier = Modifier
                .padding(32.dp),
            totalIndicators = slideItems.size,
            currentIndicator = pagerState.currentPage
        )
    }
}

@Preview
@Composable
private fun WelcomeSlideUIPreview() {
    val slideItems = listOf(
        Pair(
            first = "Bem-vindo",
            second = "O melhor aplicativo de streaming de filmes do século para tornar seus dias incríveis!"
        ),
        Pair(
            first = "Bem-vindo",
            second = "O melhor aplicativo de streaming de filmes do século para tornar seus dias incríveis!"
        ),
        Pair(
            first = "Bem-vindo",
            second = "O melhor aplicativo de streaming de filmes do século para tornar seus dias incríveis!"
        )
    )
    val pagerState = rememberPagerState {
        slideItems.size
    }

    HelloTheme(isDarkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HelloTheme.colorScheme.screen.background),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeSlideUI(
                slideItems = slideItems,
                pagerState = pagerState
            )
        }
    }
}

@Composable
fun ImageSlider(pagerState: PagerState) {
    val painter = when(pagerState.currentPage){
        0 -> R.drawable.onboarding_image_1
        1 -> R.drawable.onboarding_image_2
        else -> R.drawable.onboarding_image_3
    }
    Image(
        painter = painterResource(painter),
        modifier = Modifier
            .size(395.dp),
        contentDescription = null
    )
}