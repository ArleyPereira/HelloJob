package br.com.hellodev.common.data.model.company

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyResponse(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("logo")
    val logo: String? = null
)