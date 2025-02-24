package br.com.hellodev.design.presenter.components.textfield.default

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation

@Composable
fun TextFieldUI(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    error: String = "",
    singleLine: Boolean = true,
    maxLength: Int = Int.MAX_VALUE,
    height: Dp = TextFieldDefaults.MinHeight,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    requireKeyboardFocus: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = HelloTheme.colorScheme.defaultColor,
        backgroundColor = HelloTheme.colorScheme.alphaDefaultColor
    )
    val borderColor = if (isFocused && isError) {
        HelloTheme.colorScheme.alertColor
    } else if (isFocused) {
        HelloTheme.colorScheme.defaultColor
    } else if (isError) {
        HelloTheme.colorScheme.alertColor
    } else {
        Color.Transparent
    }

    val backgroundColor = if (isFocused && isError) {
        HelloTheme.colorScheme.alertAlphaColor
    } else if (isFocused) {
        HelloTheme.colorScheme.alphaDefaultColor
    } else if (isError) {
        HelloTheme.colorScheme.alertAlphaColor
    } else {
        HelloTheme.colorScheme.textField.background
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.4.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(500),
                color = HelloTheme.colorScheme.text.color,
                letterSpacing = 0.2.sp
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
            TextField(
                value = value,
                onValueChange = { value ->
                    val filteredValue = when (visualTransformation) {
                        is MaskVisualTransformation -> value.filter { it.isDigit() }

                        else -> value
                    }

                    if (filteredValue.length <= maxLength) {
                        onValueChange(filteredValue)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }
                    .focusRequester(focusRequester),
                enabled = enabled,
                readOnly = readOnly,
//                label = {
//                    Text(
//                        fontFamily = UrbanistFamily,
//                        text = label,
//                        style = if (isFocused) {
//                            TextStyle(
//                                fontSize = 12.sp,
//                                lineHeight = 11.sp,
//                                fontFamily = UrbanistFamily,
//                                fontWeight = FontWeight(700),
//                                color = HelloTheme.colorScheme.defaultColor
//                            )
//                        } else {
//                            TextStyle(
//                                lineHeight = 15.4.sp,
//                                fontFamily = UrbanistFamily,
//                                color = HelloTheme.colorScheme.textField.placeholder
//                            )
//                        }
//                    )
//                },
                placeholder = {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            lineHeight = 19.6.sp,
                            fontFamily = UrbanistFamily,
                            color = HelloTheme.colorScheme.textField.placeholder,
                            letterSpacing = 0.2.sp
                        )
                    )
                },
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                singleLine = singleLine,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = backgroundColor,
                    focusedContainerColor = backgroundColor,
                    disabledContainerColor = backgroundColor,
                    errorContainerColor = backgroundColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedTextColor = HelloTheme.colorScheme.textField.text,
                    focusedTextColor = HelloTheme.colorScheme.textField.text,
                    errorTextColor = HelloTheme.colorScheme.textField.text,
                    disabledTextColor = HelloTheme.colorScheme.textField.disabledText,
                    cursorColor = HelloTheme.colorScheme.defaultColor
                )
            )
        }

        if (isError) {
            Text(
                text = error,
                modifier = Modifier
                    .padding(start = 16.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 19.6.sp,
                    fontFamily = UrbanistFamily,
                    color = HelloTheme.colorScheme.alertColor,
                    letterSpacing = 0.2.sp
                )
            )
        }

        if (requireKeyboardFocus) {
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun TextFieldUIPreview() {
    var textValue by remember {
        mutableStateOf("testando")
    }

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(HelloTheme.colorScheme.screen.backgroundPrimary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldUI(
                modifier = Modifier
                    .padding(32.dp),
                value = textValue,
                isError = true,
                error = "Nome inválido",
                label = "label",
                placeholder = "placeholder",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock_password),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {},
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_hide),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    )
                },
                onValueChange = {
                    textValue = it
                }
            )

            TextFieldUI(
                modifier = Modifier
                    .padding(32.dp),
                value = textValue,
                label = "label",
                placeholder = "placeholder",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock_password),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {},
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_hide),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    )
                },
                onValueChange = {
                    textValue = it
                }
            )
        }
    }
}