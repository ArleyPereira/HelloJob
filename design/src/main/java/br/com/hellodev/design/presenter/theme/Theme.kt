package br.com.hellodev.design.presenter.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import br.com.hellodev.design.presenter.theme.scheme.BorderColorScheme
import br.com.hellodev.design.presenter.theme.scheme.MyColorScheme
import br.com.hellodev.design.presenter.theme.scheme.ButtonColorScheme
import br.com.hellodev.design.presenter.theme.scheme.DividerColorScheme
import br.com.hellodev.design.presenter.theme.scheme.IconColorScheme
import br.com.hellodev.design.presenter.theme.scheme.ScreenColorScheme
import br.com.hellodev.design.presenter.theme.scheme.SocialButtonColorScheme
import br.com.hellodev.design.presenter.theme.scheme.SwitchColorScheme
import br.com.hellodev.design.presenter.theme.scheme.TextColorScheme
import br.com.hellodev.design.presenter.theme.scheme.TextFieldColorScheme
import br.com.hellodev.design.presenter.theme.scheme.TopAppBarColorScheme

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
        primaryBackground = PrimaryButtonColor,
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
        background = BackgroundSocialButtonColorLight,
        border = BorderSocialButtonColorLight,
        text = TextSocialButtonColorLight
    ),
    textField = TextFieldColorScheme(
        background = TextFieldBackgroundColorLight,
        text = TextFieldTextColorLight
    ),
    switch = SwitchColorScheme(
        selectedBackground = SwitchSelectedBackgroundColor,
        unselectedBackground = SwitchUnselectedBackgroundColor
    ),
    topAppBar = TopAppBarColorScheme(
        background = TopAppBarColorLight,
        content = TopAppBarContentColorLight
    ),
    defaultColor = DefaultColor,
    disabledDefaultColor = DisabledDefaultColor,
    alphaDefaultColor = AlphaDefaultColor,
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
        primaryBackground = PrimaryButtonColor,
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
        background = BackgroundSocialButtonColorDark,
        border = BorderSocialButtonColorDark,
        text = TextSocialButtonColorDark
    ),
    textField = TextFieldColorScheme(
        background = TextFieldBackgroundColorDark,
        text = TextFieldTextColorDark
    ),
    switch = SwitchColorScheme(
        selectedBackground = SwitchSelectedBackgroundColor,
        unselectedBackground = SwitchUnselectedBackgroundColor
    ),
    topAppBar = TopAppBarColorScheme(
        background = TopAppBarColorDark,
        content = TopAppBarContentColorDark
    ),
    defaultColor = DefaultColor,
    disabledDefaultColor = DisabledDefaultColor,
    alphaDefaultColor = AlphaDefaultColor,
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