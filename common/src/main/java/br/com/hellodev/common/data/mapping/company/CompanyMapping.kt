package br.com.hellodev.common.data.mapping.company

import br.com.hellodev.common.data.model.company.CompanyResponse
import br.com.hellodev.common.domain.model.company.CompanyDomain

fun CompanyResponse.toDomain(): CompanyDomain {
    return CompanyDomain(
        id = id,
        name = name,
        logo = logo
    )
}