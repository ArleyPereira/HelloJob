package br.com.hellodev.main.presenter.features.account.screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.core.enums.account.AccountCardActionType.ADD
import br.com.hellodev.core.enums.account.AccountCardActionType.EDIT
import br.com.hellodev.core.enums.account.AccountCardType
import br.com.hellodev.core.enums.account.AccountCardType.CONTACT_INFORMATION
import br.com.hellodev.core.enums.icon.IconType
import br.com.hellodev.core.enums.icon.IconType.IC_EMAIL_LINE
import br.com.hellodev.core.enums.icon.IconType.IC_LOCATION
import br.com.hellodev.core.enums.icon.IconType.IC_PHONE
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.card.account.AccountCard
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.icon.default.getIconWrawable
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.upload.UploadUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.main.presenter.features.account.action.AccountAction
import br.com.hellodev.main.presenter.features.account.state.AccountState
import br.com.hellodev.main.presenter.features.account.viewmodel.AccountViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen() {
    val viewModel = koinViewModel<AccountViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pickPdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.dispatchAction(AccountAction.OnSelectedCurriculum(it))
        }
    }

    AccountContent(
        state = state,
        action = viewModel::dispatchAction,
        onUploadClick = {
            pickPdfLauncher.launch(arrayOf("application/pdf"))
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountContent(
    state: AccountState,
    action: (AccountAction) -> Unit,
    onUploadClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                modifier = Modifier,
                title = "Perfil",
                showNavigationIcon = false,
                actions = {
                    Row(
                        modifier = Modifier
                            .padding(end = 8.dp)
                    ) {
                        IconButton(
                            onClick = { },
                            content = {
                                DefaultIcon(
                                    type = IconType.IC_SETTINGS,
                                    tint = HelloTheme.colorScheme.icon.color
                                )
                            }
                        )
                    }
                },
                onClick = {}
            )
        },
        containerColor = HelloTheme.colorScheme.screen.backgroundPrimary,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ImageUI(
                        modifier = Modifier
                            .size(60.dp),
                        imageModel = R.drawable.person,
                        contentScale = ContentScale.Crop,
                        previewPlaceholder = painterResource(id = R.drawable.person),
                        shape = CircleShape,
                        onClick = {}
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Andrew Ainsley",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 28.8.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(700),
                                color = HelloTheme.colorScheme.text.color
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "UI/UX Designer at Paypal Inc.",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 22.4.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(500),
                                color = HelloTheme.colorScheme.text.color,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }

                    DefaultIcon(
                        type = IconType.IC_EDIT_LINE,
                        onClick = {}
                    )
                }

                HorizontalDividerUI()

                AccountCard(
                    title = "Informações de contato",
                    cardType = CONTACT_INFORMATION,
                    actionType = EDIT,
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Icon(
                                    painter = painterResource(getIconWrawable(type = IC_LOCATION)),
                                    contentDescription = null,
                                    tint = HelloTheme.colorScheme.icon.color
                                )

                                Text(
                                    text = "Vitória, Espiríto Santo",
                                    style = TextStyle(
                                        lineHeight = 22.4.sp,
                                        fontFamily = UrbanistFamily,
                                        fontWeight = FontWeight(500),
                                        color = HelloTheme.colorScheme.text.color,
                                        letterSpacing = 0.2.sp
                                    )
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Icon(
                                    painter = painterResource(getIconWrawable(type = IC_PHONE)),
                                    contentDescription = null,
                                    tint = HelloTheme.colorScheme.icon.color
                                )

                                Text(
                                    text = "(27) 99637-5733",
                                    style = TextStyle(
                                        lineHeight = 22.4.sp,
                                        fontFamily = UrbanistFamily,
                                        fontWeight = FontWeight(500),
                                        color = HelloTheme.colorScheme.text.color,
                                        letterSpacing = 0.2.sp
                                    )
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Icon(
                                    painter = painterResource(getIconWrawable(type = IC_EMAIL_LINE)),
                                    contentDescription = null,
                                    tint = HelloTheme.colorScheme.icon.color
                                )

                                Text(
                                    text = "arley.santana@hellodev.com.br",
                                    style = TextStyle(
                                        lineHeight = 22.4.sp,
                                        fontFamily = UrbanistFamily,
                                        fontWeight = FontWeight(500),
                                        color = HelloTheme.colorScheme.text.color,
                                        letterSpacing = 0.2.sp
                                    )
                                )
                            }
                        }
                    },
                    onActionClick = {}
                )

                AccountCard(
                    title = "Resumo",
                    cardType = AccountCardType.SUMMARY,
                    actionType = EDIT,
                    content = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = LoremIpsum(25).values.first(),
                                style = TextStyle(
                                    lineHeight = 22.4.sp,
                                    fontFamily = UrbanistFamily,
                                    fontWeight = FontWeight(500),
                                    color = HelloTheme.colorScheme.text.color,
                                    letterSpacing = 0.2.sp
                                )
                            )
                        }
                    },
                    onActionClick = {}
                )

                AccountCard(
                    title = "Expectativa salarial",
                    cardType = AccountCardType.EXPECTED_SALARY,
                    actionType = EDIT,
                    content = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "R$ 10,000 - R$ 25,000 /mês",
                                style = TextStyle(
                                    lineHeight = 22.4.sp,
                                    fontFamily = UrbanistFamily,
                                    fontWeight = FontWeight(500),
                                    color = HelloTheme.colorScheme.text.color,
                                    letterSpacing = 0.2.sp
                                )
                            )
                        }
                    },
                    onActionClick = {}
                )

                AccountCard(
                    title = "Experiência profissional",
                    cardType = AccountCardType.WORK_EXPERIENCE,
                    actionType = ADD,
                    content = {},
                    onActionClick = {}
                )

                AccountCard(
                    title = "Educação",
                    cardType = AccountCardType.EDUCATION,
                    actionType = ADD,
                    content = {},
                    onActionClick = {}
                )

                AccountCard(
                    title = "Projetos",
                    cardType = AccountCardType.PROJECTS,
                    actionType = ADD,
                    content = {},
                    onActionClick = {}
                )

                AccountCard(
                    title = "Idiomas",
                    cardType = AccountCardType.LANGUAGES,
                    actionType = ADD,
                    content = {},
                    onActionClick = {}
                )

                AccountCard(
                    title = "Tecnologias",
                    cardType = AccountCardType.SKILLS,
                    actionType = EDIT,
                    content = {},
                    onActionClick = {}
                )

                AccountCard(
                    title = "Curriculo",
                    cardType = AccountCardType.CV_RESUME,
                    actionType = null,
                    content = {
                        UploadUI(
                            isLoading = state.isUploading,
                            uri = state.uriPdf,
                            onLoadClick = onUploadClick,
                            onDeleteClick = {
                                action(AccountAction.OnDeleteCurriculum)
                            }
                        )
                    },
                    onActionClick = {}
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun AccountPreview() {
    HelloTheme {
        AccountContent(
            state = AccountState(),
            action = {},
            onUploadClick = {}
        )
    }
}