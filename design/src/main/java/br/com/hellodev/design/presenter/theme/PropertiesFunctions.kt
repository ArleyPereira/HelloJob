package br.com.hellodev.design.presenter.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Border Stroke None
val BorderStrokeNone = BorderStroke(
    width = 0.dp,
    color = Color.Transparent
)

val ShapeBottomSheet = RoundedCornerShape(
    topStart = 32.dp,
    topEnd = 32.dp
)

@Composable
fun Modifier.borderDefault(
    width: Dp = 1.dp,
    color: Color = HelloTheme.colorScheme.border.unselected,
    shape: Shape = CircleShape
): Modifier {
    return this
        .clip(shape)
        .drawBehind {
            drawOutline(
                outline = shape.createOutline(size, layoutDirection, this),
                color = color,
                style = Stroke(width.toPx())
            )
        }
}

fun Modifier.dashedBorder(
    brush: Brush,
    shape: Shape,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = this.drawWithContent {

    val outline = shape.createOutline(size, layoutDirection, density = this)

    val dashedStroke = Stroke(
        cap = cap,
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLength.toPx(), gapLength.toPx())
        )
    )

    drawContent()

    drawOutline(
        outline = outline,
        style = dashedStroke,
        brush = brush
    )
}

@Composable
fun borderStrokeDefault(isSelect: Boolean = false): BorderStroke {
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
fun iconTintColor(
    filled: Boolean,
    isError: Boolean = false
): Color {
    return when {
        isError -> HelloTheme.colorScheme.alertColor
        filled -> HelloTheme.colorScheme.defaultColor
        else -> HelloTheme.colorScheme.icon.color
    }
}
