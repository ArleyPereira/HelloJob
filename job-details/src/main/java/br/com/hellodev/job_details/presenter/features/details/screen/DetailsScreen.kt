package br.com.hellodev.job_details.presenter.features.details.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.hellodev.common.domain.model.job.item.JobItemDomain
import br.com.hellodev.core.enums.icon.IconType
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.bar.top.TopAppBarUI
import br.com.hellodev.design.presenter.components.bottom.sheet.content.apply.ApplyJobSheetContent
import br.com.hellodev.design.presenter.components.bottom.sheet.default.DefaultBottomSheet
import br.com.hellodev.design.presenter.components.button.PrimaryButton
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.loading.CircularLoadingScreen
import br.com.hellodev.design.presenter.components.tag.JobTagUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.theme.borderStrokeDefault
import br.com.hellodev.design.presenter.theme.iconTintColor
import br.com.hellodev.job_details.presenter.features.details.action.DetailsAction
import br.com.hellodev.job_details.presenter.features.details.state.DetailsState
import br.com.hellodev.job_details.presenter.features.details.viewmodel.DetailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    navigateToApplyingScreen: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<DetailsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    DetailsContent(
        state = state,
        action = viewModel::dispatchAction,
        navigateToApplyingScreen = navigateToApplyingScreen,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsContent(
    state: DetailsState,
    action: (DetailsAction) -> Unit,
    navigateToApplyingScreen: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showBottomSheet by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }

    when {
        state.isLoading -> {
            CircularLoadingScreen()
        }

        else -> {
            Scaffold(
                topBar = {
                    TopAppBarUI(
                        actions = {
                            Row(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                            ) {
                                IconButton(
                                    onClick = { checked = !checked },
                                    content = {
                                        DefaultIcon(
                                            type = IconType.IC_SEND,
                                            tint = HelloTheme.colorScheme.icon.color
                                        )
                                    }
                                )

                                IconButton(
                                    onClick = { checked = !checked },
                                    content = {
                                        DefaultIcon(
                                            type = if (checked) IconType.IC_MARK_FILL else IconType.IC_MARK_LINE,
                                            tint = iconTintColor(filled = checked)
                                        )
                                    }
                                )
                            }
                        },
                        onClick = onBackPressed
                    )
                },
                bottomBar = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(HelloTheme.colorScheme.screen.backgroundPrimary)
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
                            text = "Aplicar",
                            onClick = { showBottomSheet = true }
                        )
                    }
                },
                containerColor = HelloTheme.colorScheme.screen.backgroundPrimary,
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(paddingValues),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(28.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = HelloTheme.colorScheme.screen.backgroundSecondary
                            ),
                            border = borderStrokeDefault(),
                            content = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .border(
                                                border = borderStrokeDefault(),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                            .size(92.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        ImageUI(
                                            modifier = Modifier
                                                .size(60.dp),
                                            imageModel = state.job?.company?.logo,
                                            contentScale = ContentScale.Crop,
                                            previewPlaceholder = painterResource(R.drawable.ic_google),
                                            enabled = false,
                                            onClick = {}
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Text(
                                        text = state.job?.title ?: "",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            lineHeight = 24.sp,
                                            fontFamily = UrbanistFamily,
                                            fontWeight = FontWeight(700),
                                            color = HelloTheme.colorScheme.text.color
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = state.job?.company?.name ?: "",
                                        style = TextStyle(
                                            lineHeight = 22.4.sp,
                                            fontFamily = UrbanistFamily,
                                            fontWeight = FontWeight(500),
                                            color = HelloTheme.colorScheme.defaultColor,
                                            letterSpacing = 0.2.sp
                                        )
                                    )

                                    HorizontalDividerUI(
                                        modifier = Modifier
                                            .padding(vertical = 16.dp)
                                    )

                                    Text(
                                        text = state.job?.address ?: "",
                                        style = TextStyle(
                                            lineHeight = 25.2.sp,
                                            fontFamily = UrbanistFamily,
                                            fontWeight = FontWeight(500),
                                            color = HelloTheme.colorScheme.text.color,
                                            letterSpacing = 0.2.sp
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = state.job?.salary ?: "",
                                        style = TextStyle(
                                            lineHeight = 25.2.sp,
                                            fontFamily = UrbanistFamily,
                                            fontWeight = FontWeight(600),
                                            color = HelloTheme.colorScheme.defaultColor,
                                            letterSpacing = 0.2.sp
                                        )
                                    )

                                    Row(
                                        modifier = Modifier
                                            .padding(vertical = 12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        state.job?.tags?.forEach { tag ->
                                            JobTagUI(
                                                text = tag.name ?: "",
                                                border = BorderStroke(
                                                    width = 1.dp,
                                                    color = HelloTheme.colorScheme.tag.border
                                                )
                                            )
                                        }
                                    }

                                    Text(
                                        text = "Publicado há 10 dias, termina em 31 de dezembro.",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            lineHeight = 19.6.sp,
                                            fontFamily = UrbanistFamily,
                                            fontWeight = FontWeight(500),
                                            color = HelloTheme.colorScheme.text.color,
                                            textAlign = TextAlign.Center,
                                            letterSpacing = 0.2.sp
                                        )
                                    )
                                }
                            }
                        )
                    }

                    if (showBottomSheet) {
                        DefaultBottomSheet(
                            onDismissRequest = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    showBottomSheet = false
                                }
                            },
                            sheetState = sheetState,
                            content = {
                                ApplyJobSheetContent(
                                    onCurriculumClick = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            showBottomSheet = false
                                            navigateToApplyingScreen(state.job?.id ?: 0)
                                        }
                                    },
                                    onProfileClick = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            showBottomSheet = false
                                        }
                                    }
                                )
                            }
                        )
                    }
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun DetailsPreview() {
    HelloTheme {
        DetailsContent(
            state = DetailsState(
                isLoading = false,
                job = JobItemDomain.items.random()
            ),
            action = {},
            navigateToApplyingScreen = {},
            onBackPressed = {}
        )
    }
}