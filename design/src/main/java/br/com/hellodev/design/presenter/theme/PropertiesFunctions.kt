package br.com.hellodev.design.presenter.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Border Stroke None
val BorderStrokeNone = BorderStroke(
    width = 0.dp,
    color = Color.Transparent
)

@Composable
fun borderStrokeDefault(isSelect: Boolean): BorderStroke {
    return if (isSelect) {
        BorderStroke(
            width = 1.dp,
            color = HelloTheme.colorScheme.border.selected
        )
    } else {
        BorderStroke(
            width = 1.dp,
            color = HelloTheme.colorScheme.border.unselected
        )
    }
}

@Composable
fun iconTintColor(filled: Boolean, isError: Boolean = false): Color {
    return if (isError) {
        HelloTheme.colorScheme.errorColor
    } else {
        if (filled) {
            HelloTheme.colorScheme.icon.color
        } else HelloTheme.colorScheme.icon.default
    }
}
