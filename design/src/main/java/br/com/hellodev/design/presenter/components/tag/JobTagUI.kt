package br.com.hellodev.design.presenter.components.tag

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.tag.TagType
import br.com.hellodev.core.enums.tag.TagType.ALERT
import br.com.hellodev.core.enums.tag.TagType.DEFAULT
import br.com.hellodev.core.enums.tag.TagType.NEUTRAL
import br.com.hellodev.core.enums.tag.TagType.SUCCESS
import br.com.hellodev.core.enums.tag.TagType.WARNING
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun JobTagUI(
    modifier: Modifier = Modifier,
    text: String,
    type: TagType = NEUTRAL,
    isAlpha: Boolean = false,
    shape: Shape = RoundedCornerShape(6.dp),
    border: BorderStroke = BorderStroke(
        width = 1.dp,
        color = Color.Transparent
    )
) {
    val backgroundColor = when (type) {
        NEUTRAL -> HelloTheme.colorScheme.tag.background
        DEFAULT -> HelloTheme.colorScheme.defaultColor
        SUCCESS -> HelloTheme.colorScheme.successColor
        WARNING -> HelloTheme.colorScheme.warningColor
        ALERT -> HelloTheme.colorScheme.alertColor
    }

    val textColor = when (type) {
        NEUTRAL -> HelloTheme.colorScheme.tag.text
        DEFAULT -> HelloTheme.colorScheme.defaultColor
        SUCCESS -> HelloTheme.colorScheme.successColor
        WARNING -> HelloTheme.colorScheme.warningColor
        ALERT -> HelloTheme.colorScheme.alertColor
    }

    val alpha = if (isAlpha) 0.1f else 1f

    Row(
        modifier = modifier
            .clip(shape = shape)
            .background(backgroundColor.copy(alpha = alpha))
            .border(
                border = border,
                shape = shape
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
                color = textColor,
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
                JobTagUI(
                    text = "Freenlance",
                    border = BorderStroke(
                        width = 1.dp,
                        color = HelloTheme.colorScheme.tag.border
                    )
                )

                JobTagUI(
                    text = "Remoto",
                    border = BorderStroke(
                        width = 1.dp,
                        color = HelloTheme.colorScheme.tag.border
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                JobTagUI(
                    text = "Freenlance",
                    type = DEFAULT,
                    isAlpha = true
                )

                JobTagUI(
                    text = "Remoto",
                    type = DEFAULT,
                    isAlpha = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                JobTagUI(
                    text = "Freenlance",
                    type = SUCCESS,
                    isAlpha = true
                )

                JobTagUI(
                    text = "Remoto",
                    type = SUCCESS,
                    isAlpha = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                JobTagUI(
                    text = "Freenlance",
                    type = WARNING,
                    isAlpha = true
                )

                JobTagUI(
                    text = "Remoto",
                    type = WARNING,
                    isAlpha = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                JobTagUI(
                    text = "Freenlance",
                    type = ALERT,
                    isAlpha = true
                )

                JobTagUI(
                    text = "Remoto",
                    type = ALERT,
                    isAlpha = true
                )
            }
        }
    }
}