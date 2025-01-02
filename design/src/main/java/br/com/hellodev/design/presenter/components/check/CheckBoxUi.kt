package br.com.hellodev.design.presenter.components.check

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.theme.BorderStrokeNone
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun CheckBoxUi(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    text: String = "",
    borderStroke: BorderStroke = BorderStrokeNone,
    onClick: () -> Unit
) {
    val painter = if (checked) {
        painterResource(id = R.drawable.ic_checked)
    } else {
        painterResource(id = R.drawable.ic_unchecked)
    }

    val textColor = if (enabled) {
        HelloTheme.colorScheme.text.color
    } else {
        HelloTheme.colorScheme.text.disabled
    }

    Row(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                enabled = enabled,
                onClick = onClick
            )
            .border(
                border = borderStroke,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = Modifier
                .size(20.dp),
            painter = painter,
            contentDescription = null
        )

        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.4.sp,
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
private fun CheckBoxUiPreview() {
    var checked by remember { mutableStateOf(false) }

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HelloTheme.colorScheme.screen.backgroundPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CheckBoxUi(
                checked = checked,
                text = "Accounting and Finance",
                onClick = {
                    checked = !checked
                }
            )
        }
    }
}