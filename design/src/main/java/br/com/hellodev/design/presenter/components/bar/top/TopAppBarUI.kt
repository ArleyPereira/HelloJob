package br.com.hellodev.design.presenter.components.bar.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUI(
    modifier: Modifier = Modifier,
    title: String = "",
    showNavigationIcon: Boolean = true,
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 28.8.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    onClick = onClick,
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                )
            } else {
                Spacer(modifier = Modifier.width(16.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = HelloTheme.colorScheme.topAppBar.background,
            navigationIconContentColor = HelloTheme.colorScheme.topAppBar.content,
            actionIconContentColor = HelloTheme.colorScheme.topAppBar.content,
            titleContentColor = HelloTheme.colorScheme.topAppBar.content
        )
    )
}

@PreviewLightDark
@Composable
private fun TopAppBarUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HelloTheme.colorScheme.screen.backgroundPrimary),
        ) {
            TopAppBarUI(
                title = "Fill Your Profile",
                showNavigationIcon = true,
                onClick = {}
            )
        }
    }
}