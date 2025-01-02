package br.com.hellodev.common.data.model.job.section

import br.com.hellodev.common.data.model.category.CategoryResponse
import br.com.hellodev.common.data.model.job.item.JobItemResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobSectionResponse(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("left_title")
    val leftTitle: String? = null,

    @SerialName("right_title")
    val rightTitle: String? = null,

    @SerialName("categories")
    val categories: List<CategoryResponse>? = null,

    @SerialName("items")
    val items: List<JobItemResponse>? = null
)
