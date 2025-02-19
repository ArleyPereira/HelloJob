package br.com.hellodev.design.presenter.components.textfield.decimal

import DecimalFormatter
import DecimalVisualTransformation
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun DecimalTextFieldUI(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeholder: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    error: String = "",
    maxValue: Float = Float.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {
    val decimalFormatter = DecimalFormatter()
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = HelloTheme.colorScheme.defaultColor,
        backgroundColor = HelloTheme.colorScheme.alphaDefaultColor
    )

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
                    val cleanedValue = decimalFormatter.cleanup(value)
                    if (DecimalFormatter.getValue(cleanedValue) <= maxValue) {
                        onValueChange(cleanedValue)
                    }else {
                        onValueChange(decimalFormatter.cleanup(maxValue.toString()))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (isError) {
                            HelloTheme.colorScheme.alertColor
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(16.dp)
                    )
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }
                    .focusRequester(focusRequester),
                enabled = enabled,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight(600),
                    color = HelloTheme.colorScheme.text.color,
                    letterSpacing = 0.2.sp
                ),
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
                prefix = {
                    Text(
                        text = "R$ ",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight(600),
                            color = HelloTheme.colorScheme.text.color,
                            letterSpacing = 0.2.sp
                        )
                    )
                },
                isError = isError,
                visualTransformation = DecimalVisualTransformation(decimalFormatter),
                keyboardOptions = keyboardOptions,
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = HelloTheme.colorScheme.textField.background,
                    focusedContainerColor = HelloTheme.colorScheme.textField.background,
                    disabledContainerColor = HelloTheme.colorScheme.textField.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorContainerColor = HelloTheme.colorScheme.alertAlphaColor,
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
    }
}

@PreviewLightDark
@Composable
private fun DecimalTextFieldUIPreview() {
    var value by remember { mutableStateOf("1090") }

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(HelloTheme.colorScheme.screen.backgroundPrimary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DecimalTextFieldUI(
                modifier = Modifier
                    .padding(32.dp),
                value = value,
                isError = true,
                error = "Nome inválido",
                placeholder = "Ex: Arley Santana",
                onValueChange = {
                    value = it
                }
            )

            DecimalTextFieldUI(
                modifier = Modifier
                    .padding(32.dp),
                value = value,
                placeholder = "Ex: Arley Santana",
                onValueChange = {
                    value = it
                }
            )
        }
    }
}