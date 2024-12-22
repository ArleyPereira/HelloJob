package br.com.hellodev.setup.presenter.features.profile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.input.InputType.*
import br.com.hellodev.core.functions.inputErrorMessage
import br.com.hellodev.core.mask.MaskVisualTransformation
import br.com.hellodev.core.mask.MaskVisualTransformation.Companion.BIRTH_DATE_MASK
import br.com.hellodev.core.mask.MaskVisualTransformation.Companion.PHONE_MASK
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.textfield.click.TextFieldClickUI
import br.com.hellodev.design.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.iconTintColor
import br.com.hellodev.setup.R
import br.com.hellodev.setup.presenter.features.profile.action.ProfileAction
import br.com.hellodev.setup.presenter.features.profile.parameter.ProfileParameter
import br.com.hellodev.setup.presenter.features.profile.state.ProfileState
import br.com.hellodev.setup.presenter.features.profile.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    parameterBackResult: ProfileParameter? = null,
    navigateToCountryScreen: () -> Unit,
    navigateToGenreScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileContent(
        state = state,
        action = viewModel::dispatchAction,
        navigateToCountryScreen = navigateToCountryScreen,
        navigateToGenreScreen = navigateToGenreScreen,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun ProfileContent(
    state: ProfileState,
    action: (ProfileAction) -> Unit,
    navigateToCountryScreen: () -> Unit,
    navigateToGenreScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = stringResource(R.string.label_title_edit_profile_screen),
                onClick = onBackPressed
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HelloTheme.colorScheme.screen.background)
                    .windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                HorizontalDividerUI()

                PrimaryButton(
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 24.dp,
                            bottom = 32.dp
                        ),
                    text = stringResource(R.string.label_button_update_edit_profile_screen),
                    onClick = { action(ProfileAction.Update) }
                )
            }
        },
        containerColor = HelloTheme.colorScheme.screen.background,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(160.dp)
                ) {
                    ImageUI(
                        modifier = Modifier
                            .size(160.dp),
                        imageModel = R.drawable.ic_person,
                        contentScale = ContentScale.Crop,
                        previewPlaceholder = painterResource(id = R.drawable.ic_person),
                        shape = CircleShape,
                        isLoading = state.isLoading,
                        onClick = {}
                    )

                    Icon(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = (-4).dp, y = (-4).dp),
                        tint = HelloTheme.colorScheme.defaultColor
                    )
                }

                TextFieldUI(
                    value = state.name,
                    placeholder = stringResource(R.string.label_input_first_name_edit_profile_screen),
                    isError = state.inputError == FIRST_NAME,
                    error = stringResource(inputErrorMessage(FIRST_NAME)),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        action(ProfileAction.OnTextFieldChanged(it, FIRST_NAME))
                    }
                )

                TextFieldUI(
                    value = state.surname,
                    placeholder = stringResource(R.string.label_input_surname_edit_profile_screen),
                    isError = state.inputError == SURNAME,
                    error = stringResource(inputErrorMessage(SURNAME)),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        action(ProfileAction.OnTextFieldChanged(it, SURNAME))
                    }
                )

                TextFieldUI(
                    value = state.dateBirth,
                    placeholder = stringResource(R.string.label_input_date_birth_edit_profile_screen),
                    isError = state.inputError == DATE_BIRTH,
                    error = stringResource(inputErrorMessage(DATE_BIRTH)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    maxLength = MaskVisualTransformation.BIRTH_DATE_MASK_SIZE,
                    visualTransformation = MaskVisualTransformation(BIRTH_DATE_MASK),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = null,
                            tint = iconTintColor(
                                filled = state.dateBirth.isNotEmpty(),
                                isError = state.inputError == DATE_BIRTH
                            )
                        )
                    },
                    onValueChange = {
                        action(ProfileAction.OnTextFieldChanged(it, DATE_BIRTH))
                    }
                )

                TextFieldUI(
                    value = state.email,
                    placeholder = stringResource(R.string.label_input_email_edit_profile_screen),
                    isError = state.inputError == EMAIL,
                    error = stringResource(inputErrorMessage(EMAIL)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = null,
                            tint = iconTintColor(
                                filled = state.email.isNotEmpty(),
                                isError = state.inputError == EMAIL
                            )
                        )
                    },
                    onValueChange = {
                        action(ProfileAction.OnTextFieldChanged(it, EMAIL))
                    }
                )

                TextFieldUI(
                    value = state.phone,
                    placeholder = stringResource(R.string.label_input_phone_edit_profile_screen),
                    isError = state.inputError == PHONE,
                    error = stringResource(inputErrorMessage(PHONE)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    maxLength = MaskVisualTransformation.PHONE_MASK_SIZE,
                    visualTransformation = MaskVisualTransformation(PHONE_MASK),
                    onValueChange = {
                        action(ProfileAction.OnTextFieldChanged(it, PHONE))
                    }
                )

                TextFieldClickUI(
                    value = state.genre,
                    placeholder = stringResource(R.string.label_input_genre_edit_profile_screen),
                    painter = painterResource(id = R.drawable.ic_right),
                    error = stringResource(inputErrorMessage(GENRE)),
                    isError = state.inputError == GENRE,
                    onClick = navigateToGenreScreen
                )

                TextFieldClickUI(
                    value = state.country,
                    placeholder = stringResource(R.string.label_input_country_edit_profile_screen),
                    painter = painterResource(id = R.drawable.ic_right),
                    error = stringResource(inputErrorMessage(COUNTRY)),
                    isError = state.inputError == COUNTRY,
                    onClick = navigateToCountryScreen
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun ProfilePreview() {
    HelloTheme {
        ProfileContent(
            state = ProfileState(
                inputError = FIRST_NAME
            ),
            action = {},
            navigateToCountryScreen = {},
            navigateToGenreScreen = {},
            onBackPressed = {}
        )
    }
}