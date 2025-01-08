package br.com.hellodev.design.presenter.components.item.job.item

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.common.domain.model.job.item.JobItemDomain
import br.com.hellodev.core.enums.icon.IconType
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.tag.JobTagUI
import br.com.hellodev.design.presenter.theme.BorderStrokeNone
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.theme.borderStrokeDefault
import br.com.hellodev.design.presenter.theme.iconTintColor

@Composable
fun JobItemUI(
    modifier: Modifier = Modifier,
    job: JobItemDomain? = null,
    onSaveClick: (Int) -> Unit,
    onClick: (Int) -> Unit
) {
    Card(
        onClick = { onClick(job?.id ?: 0) },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = HelloTheme.colorScheme.screen.backgroundSecondary
        ),
        border = if (isSystemInDarkTheme()) {
            BorderStrokeNone
        } else {
            borderStrokeDefault()
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 20.dp
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .border(
                                    border = borderStrokeDefault(),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .size(64.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            ImageUI(
                                modifier = Modifier
                                    .size(32.dp),
                                imageModel = job?.company?.logo,
                                contentScale = ContentScale.Crop,
                                previewPlaceholder = painterResource(R.drawable.ic_google),
                                onClick = { onClick(job?.id ?: 0) }
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = job?.title ?: "",
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
                                text = job?.company?.name ?: "",
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

                    val checked = false
                    DefaultIcon(
                        type = if(checked) IconType.IC_MARK_FILL else IconType.IC_MARK_LINE,
                        tint = iconTintColor(filled = checked),
                        onClick = { onSaveClick(job?.id ?: 0) }
                    )
                }

                HorizontalDividerUI(
                    modifier = Modifier
                        .padding(
                            vertical = 12.dp,
                            horizontal = 20.dp
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            bottom = 20.dp
                        )
                ) {
                    Spacer(modifier = Modifier.width(80.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = job?.address ?: "",
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
                            text = job?.salary ?: "",
                            style = TextStyle(
                                lineHeight = 25.2.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(600),
                                color = HelloTheme.colorScheme.defaultColor,
                                letterSpacing = 0.2.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(
                                end = 16.dp
                            ),
                        ) {
                            items(
                                items = job?.tags ?: emptyList(),
                                key = { it.id ?: 0 }
                            ) { tag ->
                                JobTagUI(text = tag.name ?: "")
                            }
                        }
                    }
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun JobItemUIPreview() {
    HelloTheme {
        JobItemUI(
            job = JobItemDomain.items.random(),
            onSaveClick = {},
            onClick = {}
        )
    }
}