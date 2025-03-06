package br.com.hellodev.data.model.home

import br.com.hellodev.data.model.banner.BannerResponse
import br.com.hellodev.data.model.job.section.JobSectionResponse
import br.com.hellodev.data.model.user.UserResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("profile")
    val profile: UserResponse? = null,

    @SerialName("banners")
    val banners: List<BannerResponse>? = null,

    @SerialName("recommendation")
    val recommendation: JobSectionResponse? = null,

    @SerialName("recent")
    val recent: JobSectionResponse? = null
)
