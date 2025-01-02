package br.com.hellodev.design.presenter.components.item.job.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.common.domain.model.category.CategoryDomain
import br.com.hellodev.common.domain.model.job.section.JobSectionDomain
import br.com.hellodev.design.presenter.components.empty.EmptyUI
import br.com.hellodev.design.presenter.components.item.job.item.JobItemUI
import br.com.hellodev.design.presenter.components.tag.CategoryTagUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun HorizontalJobSectionUI(
    section: JobSectionDomain? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        JobHeaderSection(
            leftTitle = section?.leftTitle,
            rightTitle = section?.rightTitle
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(
                items = section?.items ?: emptyList(),
                key = { it.id ?: 0 }
            ) { job ->
                JobItemUI(
                    modifier = Modifier
                        .fillParentMaxWidth(0.95f),
                    job = job
                )
            }
        }
    }
}

fun LazyListScope.verticalJobSectionUI(
    section: JobSectionDomain? = null,
    categorySelected: CategoryDomain? = null,
    search: String? = "",
    onCategorySelected: (CategoryDomain?) -> Unit
) {
    item {
        Spacer(modifier = Modifier.height(8.dp))

        JobHeaderSection(
            leftTitle = section?.leftTitle,
            rightTitle = section?.rightTitle
        )
    }

    item {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(
                items = section?.categories ?: emptyList(),
                key = { it.id ?: 0 }
            ) { category ->
                CategoryTagUI(
                    text = category.name ?: "",
                    isSelect = category.id == categorySelected?.id,
                    onClick = { onCategorySelected(category) }
                )
            }
        }
    }

    item {
        if (section?.items?.isEmpty() == true) {
            EmptyUI(
                title = "Nenhuma vaga encontrada",
                description = "Com o termo pesquisado: $search",
            )
        }
    }

    items(
        items = section?.items ?: emptyList(),
        key = { it.id ?: 0 }
    ) { job ->
        JobItemUI(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            job = job
        )
    }
}

@Composable
fun JobHeaderSection(
    modifier: Modifier = Modifier,
    rightTitle: String?,
    leftTitle: String?
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = leftTitle ?: "",
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(700),
                color = HelloTheme.colorScheme.text.color
            )
        )

        Text(
            text = rightTitle ?: "",
            style = TextStyle(
                lineHeight = 22.4.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(700),
                color = HelloTheme.colorScheme.defaultColor,
                letterSpacing = 0.2.sp
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun JobSectionUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HelloTheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            HorizontalJobSectionUI()
        }
    }
}