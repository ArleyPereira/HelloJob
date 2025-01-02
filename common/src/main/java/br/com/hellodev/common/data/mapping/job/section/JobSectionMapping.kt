package br.com.hellodev.common.data.mapping.job.section

import br.com.hellodev.common.data.mapping.category.toDomain
import br.com.hellodev.common.data.mapping.job.item.toDomain
import br.com.hellodev.common.data.model.job.section.JobSectionResponse
import br.com.hellodev.common.domain.model.job.section.JobSectionDomain

fun JobSectionResponse.toDomain(): JobSectionDomain {
    return JobSectionDomain(
        id = id,
        leftTitle = leftTitle,
        rightTitle = rightTitle,
        categories = categories?.map { it.toDomain() },
        items = items?.map { it.toDomain() }
    )
}