package br.com.hellodev.design.presenter.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun TextFieldPasswordUI(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    maxLength: Int = Int.MAX_VALUE,
    requireKeyboardFocus: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done
    ),
    onValueChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    val customTextSelectionColors = TextSelectionColors(
        handleColor = HelloTheme.colorScheme.defaultColor,
        backgroundColor = HelloTheme.colorScheme.alphaDefaultColor
    )

    var showPassword by remember { mutableStateOf(false) }

    val visualTransformation = if (showPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
            TextField(
                value = value,
                onValueChange = {
                    if (it.length <= maxLength) {
                        onValueChange(it)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (isError) {
                            HelloTheme.colorScheme.defaultColor
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .focusRequester(focusRequester),
                enabled = enabled,
                placeholder = {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            lineHeight = 19.6.sp,
                            fontFamily = UrbanistFamily,
                            color = HelloTheme.colorScheme.greyscale500Color,
                            letterSpacing = 0.2.sp
                        )
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock_password),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { showPassword = !showPassword },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_hide),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    )
                },
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = HelloTheme.colorScheme.textField.background,
                    focusedContainerColor = HelloTheme.colorScheme.textField.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorContainerColor = HelloTheme.colorScheme.alphaDefaultColor,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedTextColor = HelloTheme.colorScheme.textField.text,
                    focusedTextColor = HelloTheme.colorScheme.textField.text,
                    errorTextColor = HelloTheme.colorScheme.textField.text,
                    cursorColor = HelloTheme.colorScheme.defaultColor
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
private fun TextFieldPasswordUIPreview() {
    var textValue by remember {
        mutableStateOf("testando")
    }

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HelloTheme.colorScheme.screen.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldPasswordUI(
                modifier = Modifier
                    .padding(32.dp),
                value = textValue,
                placeholder = "Ex: Arley Santana",
                onValueChange = {
                    textValue = it
                }
            )
        }
    }
}