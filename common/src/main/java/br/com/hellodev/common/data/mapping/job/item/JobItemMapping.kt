package br.com.hellodev.common.data.mapping.job.item

import br.com.hellodev.common.data.mapping.company.toDomain
import br.com.hellodev.common.data.mapping.tag.toDomain
import br.com.hellodev.common.data.model.job.item.JobItemResponse
import br.com.hellodev.common.domain.model.job.item.JobItemDomain

fun JobItemResponse.toDomain(): JobItemDomain {
    return JobItemDomain(
        id = id,
        title = title,
        address = address,
        salary = salary,
        company = company?.toDomain(),
        tags = tags?.map { it.toDomain() }
    )
}