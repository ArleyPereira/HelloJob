package br.com.hellodev.design.presenter.components.icon.default

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.hellodev.core.enums.icon.IconType
import br.com.hellodev.core.enums.icon.IconType.IC_ARROW_LEFT
import br.com.hellodev.core.enums.icon.IconType.IC_ARROW_RIGHT
import br.com.hellodev.core.enums.icon.IconType.IC_CALENDAR
import br.com.hellodev.core.enums.icon.IconType.IC_CLASSIFY
import br.com.hellodev.core.enums.icon.IconType.IC_CLOSE
import br.com.hellodev.core.enums.icon.IconType.IC_EDIT
import br.com.hellodev.core.enums.icon.IconType.IC_EMAIL
import br.com.hellodev.core.enums.icon.IconType.IC_ERROR
import br.com.hellodev.core.enums.icon.IconType.IC_FACEBOOK
import br.com.hellodev.core.enums.icon.IconType.IC_FILTER
import br.com.hellodev.core.enums.icon.IconType.IC_GITHUB
import br.com.hellodev.core.enums.icon.IconType.IC_GOOGLE
import br.com.hellodev.core.enums.icon.IconType.IC_HIDE
import br.com.hellodev.core.enums.icon.IconType.IC_LOCK_PASSWORD
import br.com.hellodev.core.enums.icon.IconType.IC_MARK_FILL
import br.com.hellodev.core.enums.icon.IconType.IC_MARK_LINE
import br.com.hellodev.core.enums.icon.IconType.IC_PDF
import br.com.hellodev.core.enums.icon.IconType.IC_RIGHT
import br.com.hellodev.core.enums.icon.IconType.IC_SEARCH
import br.com.hellodev.core.enums.icon.IconType.IC_SEND
import br.com.hellodev.core.enums.icon.IconType.IC_SHOW
import br.com.hellodev.core.enums.icon.IconType.IC_SUCCESS
import br.com.hellodev.core.enums.icon.IconType.IC_UPLOAD
import br.com.hellodev.design.R

@Composable
fun DefaultIcon(
    type: IconType,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit = {}
) {
    val painter = painterResource(
        when (type) {
            IC_FILTER -> R.drawable.ic_filter
            IC_EMAIL -> R.drawable.ic_email
            IC_FACEBOOK -> R.drawable.ic_facebook
            IC_GOOGLE -> R.drawable.ic_google
            IC_GITHUB -> R.drawable.ic_github
            IC_HIDE -> R.drawable.ic_hide
            IC_SHOW -> R.drawable.ic_show
            IC_LOCK_PASSWORD -> R.drawable.ic_lock_password
            IC_CLASSIFY -> R.drawable.ic_classify
            IC_SEARCH -> R.drawable.ic_search
            IC_EDIT -> R.drawable.ic_edit
            IC_CALENDAR -> R.drawable.ic_calendar
            IC_RIGHT -> R.drawable.ic_right
            IC_MARK_FILL -> R.drawable.ic_mark_fill
            IC_MARK_LINE -> R.drawable.ic_mark_line
            IC_SEND -> R.drawable.ic_send
            IC_UPLOAD -> R.drawable.ic_upload
            IC_PDF -> R.drawable.ic_pdf
            IC_CLOSE -> R.drawable.ic_close
            IC_ERROR -> R.drawable.ic_error
            IC_SUCCESS -> R.drawable.ic_success
            IC_ARROW_LEFT -> R.drawable.ic_arrow_left
            IC_ARROW_RIGHT -> R.drawable.ic_arrow_right
        }
    )

    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            ),
        tint = tint
    )
}

@Preview
@Composable
private fun DefaultIconPreview() {
    DefaultIcon(
        modifier = Modifier,
        type = IC_FILTER,
        contentDescription = null,
        tint = Color.Unspecified,
        onClick = {}
    )
}