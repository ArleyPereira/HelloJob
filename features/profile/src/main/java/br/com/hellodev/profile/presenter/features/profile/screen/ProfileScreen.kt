package br.com.hellodev.profile.presenter.features.profile.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.input.InputType.COUNTRY
import br.com.hellodev.core.enums.input.InputType.DATE_BIRTH
import br.com.hellodev.core.enums.input.InputType.EMAIL
import br.com.hellodev.core.enums.input.InputType.FIRST_NAME
import br.com.hellodev.core.enums.input.InputType.GENRE
import br.com.hellodev.core.enums.input.InputType.PHONE
import br.com.hellodev.core.enums.input.InputType.SURNAME
import br.com.hellodev.core.functions.inputErrorMessage
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.textfield.click.TextFieldClickUI
import br.com.hellodev.design.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.design.presenter.components.textfield.dropdown.TextFieldDropdown
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation.Companion.BIRTH_DATE_MASK
import br.com.hellodev.design.presenter.util.transformation.MaskVisualTransformation.Companion.PHONE_MASK
import br.com.hellodev.profile.R
import br.com.hellodev.profile.presenter.features.profile.action.ProfileAction
import br.com.hellodev.profile.presenter.features.profile.state.ProfileState
import br.com.hellodev.profile.presenter.features.profile.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navigateToCountryScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {

    }

    LaunchedEffect(state.navigateToHomeScreen) {
        if (state.navigateToHomeScreen) {

        }
    }

    ProfileContent(
        state = state,
        action = viewModel::dispatchAction,
        navigateToCountryScreen = navigateToCountryScreen,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun ProfileContent(
    state: ProfileState,
    action: (ProfileAction) -> Unit,
    navigateToCountryScreen: () -> Unit,
    onBackPressed: () -> Unit
) {
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            action(ProfileAction.OnChangeImage(uri))
        }
    )

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
                    .background(HelloTheme.colorScheme.screen.backgroundPrimary)
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .imePadding()
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
        containerColor = HelloTheme.colorScheme.screen.backgroundPrimary,
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
                        imageModel = state.selectedImageUri ?: R.drawable.placeholder_photo_profile,
                        contentScale = ContentScale.Crop,
                        shape = CircleShape,
                        isLoading = state.isLoading,
                        previewPlaceholder = painterResource(R.drawable.placeholder_photo_profile),
                        onClick = {
                            imagePickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                    )

                    DefaultIcon(
                        type = IllustrationType.IC_EDIT_FILL,
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = (-4).dp, y = (-4).dp),
                        tint = HelloTheme.colorScheme.defaultColor
                    )
                }

                TextFieldUI(
                    value = state.name,
                    label = stringResource(R.string.label_input_first_name_edit_profile_screen),
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
                    label = stringResource(R.string.label_input_surname_edit_profile_screen),
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
                    label = stringResource(R.string.label_input_date_birth_edit_profile_screen),
                    placeholder = stringResource(R.string.label_input_date_birth_edit_profile_screen),
                    isError = state.inputError == DATE_BIRTH,
                    error = stringResource(inputErrorMessage(DATE_BIRTH)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    maxLength = MaskVisualTransformation.BIRTH_DATE_MASK_SIZE,
                    visualTransformation = MaskVisualTransformation(BIRTH_DATE_MASK),
                    onValueChange = {
                        action(ProfileAction.OnTextFieldChanged(it, DATE_BIRTH))
                    }
                )

                TextFieldClickUI(
                    value = state.email,
                    label = stringResource(R.string.label_input_email_edit_profile_screen),
                    illustrationType = IllustrationType.IC_RIGHT,
                    error = stringResource(inputErrorMessage(EMAIL)),
                    isError = state.inputError == EMAIL,
                    onClick = {}
                )

                TextFieldClickUI(
                    value = MaskVisualTransformation.maskText(state.phone, PHONE_MASK),
                    label = stringResource(R.string.label_input_phone_edit_profile_screen),
                    illustrationType = IllustrationType.IC_RIGHT,
                    error = stringResource(inputErrorMessage(PHONE)),
                    isError = state.inputError == PHONE,
                    onClick = {}
                )

                TextFieldDropdown(
                    modifier = Modifier,
                    items = state.genres,
                    label = stringResource(R.string.label_input_genre_edit_profile_screen),
                    illustrationType = IllustrationType.IC_RIGHT,
                    onOptionSelected = {
                        action(ProfileAction.OnTextFieldChanged(it, GENRE))
                    }
                )

                TextFieldClickUI(
                    value = state.country?.name ?: "",
                    label = stringResource(R.string.label_input_country_edit_profile_screen),
                    illustrationType = IllustrationType.IC_RIGHT,
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
            onBackPressed = {}
        )
    }
}