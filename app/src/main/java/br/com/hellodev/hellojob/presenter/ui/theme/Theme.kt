package br.com.hellodev.hellojob.presenter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.BorderColorScheme
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.IconColorScheme
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.MyColorScheme
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.ButtonColorScheme
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.DividerColorScheme
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.ScreenColorScheme
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.SocialButtonColorScheme
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.SwitchColorScheme
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.TextColorScheme
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.TextFieldColorScheme
import br.com.hellodev.hellojob.presenter.ui.theme.scheme.TopAppBarColorScheme

private val LightColorScheme = MyColorScheme(
    text = TextColorScheme(
        color = TextColorLight
    ),
    screen = ScreenColorScheme(
        background = BackgroundColorLight
    ),
    icon = IconColorScheme(
        color = IconColorLight
    ),
    button = ButtonColorScheme(
        primaryBackground = PrimaryButtonColorLight,
        secondaryBackground = SecondaryButtonColorLight,
        primaryText = PrimaryButtonTextColorLight,
        secondaryText = SecondaryButtonTextColorLight
    ),
    border = BorderColorScheme(
        selected = SelectedBorderColor,
        unselected = UnselectedBorderColor
    ),
    divider = DividerColorScheme(
        color = DividerColorLight
    ),
    socialButton = SocialButtonColorScheme(
        background = BackgroundSocialButtonColorLight
    ),
    textField = TextFieldColorScheme(
        background = TextFieldBackgroundColorLight
    ),
    switch = SwitchColorScheme(
        selectedBackground = SwitchSelectedBackgroundColor,
        unselectedBackground = SwitchUnselectedBackgroundColor
    ),
    topAppBar = TopAppBarColorScheme(
        background = TopAppBarColorLight
    ),
    defaultColor = DefaultColor,
    disabledDefaultColor = DisabledDefaultColor,
    successColor = SuccessColor,
    alertColor = AlertColor,
    warningColor = WarningColor,
    infoColor = InfoColor,
    disabledColor = DisabledColor,
    greyscale900Color = Greyscale900Color,
    greyscale800Color = Greyscale800Color,
    greyscale700Color = Greyscale700Color,
    greyscale600Color = Greyscale600Color,
    greyscale500Color = Greyscale500Color,
    greyscale400Color = Greyscale400Color,
    greyscale300Color = Greyscale300Color,
    greyscale200Color = Greyscale200Color,
    greyscale100Color = Greyscale100Color,
    greyscale50Color = Greyscale50Color,
    spotColor = SpotColor,
    ambientColor = AmbientColor
)

private val DarkColorScheme = MyColorScheme(
    text = TextColorScheme(
        color = TextColorDark
    ),
    screen = ScreenColorScheme(
        background = BackgroundColorDark
    ),
    icon = IconColorScheme(
        color = IconColorDark
    ),
    button = ButtonColorScheme(
        primaryBackground = PrimaryButtonColorDark,
        secondaryBackground = SecondaryButtonColorDark,
        primaryText = PrimaryButtonTextColorDark,
        secondaryText = SecondaryButtonTextColorDark
    ),
    border = BorderColorScheme(
        selected = SelectedBorderColor,
        unselected = UnselectedBorderColor
    ),
    divider = DividerColorScheme(
        color = DividerColorDark
    ),
    socialButton = SocialButtonColorScheme(
        background = BackgroundSocialButtonColorDark
    ),
    textField = TextFieldColorScheme(
        background = TextFieldBackgroundColorDark
    ),
    switch = SwitchColorScheme(
        selectedBackground = SwitchSelectedBackgroundColor,
        unselectedBackground = SwitchUnselectedBackgroundColor
    ),
    topAppBar = TopAppBarColorScheme(
        background = TopAppBarColorDark
    ),
    defaultColor = DefaultColor,
    disabledDefaultColor = DisabledDefaultColor,
    successColor = SuccessColor,
    alertColor = AlertColor,
    warningColor = WarningColor,
    infoColor = InfoColor,
    disabledColor = DisabledColor,
    greyscale900Color = Greyscale900Color,
    greyscale800Color = Greyscale800Color,
    greyscale700Color = Greyscale700Color,
    greyscale600Color = Greyscale600Color,
    greyscale500Color = Greyscale500Color,
    greyscale400Color = Greyscale400Color,
    greyscale300Color = Greyscale300Color,
    greyscale200Color = Greyscale200Color,
    greyscale100Color = Greyscale100Color,
    greyscale50Color = Greyscale50Color,
    spotColor = SpotColor,
    ambientColor = AmbientColor
)

private val LocalColorScheme = compositionLocalOf { LightColorScheme }

object HelloTheme {
    val colorScheme: MyColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current
}

@Composable
fun HelloTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme by remember(isDarkTheme) {
        mutableStateOf(if (isDarkTheme) DarkColorScheme else LightColorScheme)
    }

    CompositionLocalProvider(LocalColorScheme provides colorScheme) {
        MaterialTheme(content = content)
    }
}