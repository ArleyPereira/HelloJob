package br.com.hellodev.common.data.mapping.home

import br.com.hellodev.common.data.mapping.banner.toDomain
import br.com.hellodev.common.data.mapping.job.section.toDomain
import br.com.hellodev.common.data.mapping.user.toDomain
import br.com.hellodev.common.data.model.home.HomeResponse
import br.com.hellodev.common.domain.model.home.HomeDomain

fun HomeResponse.toDomain(): HomeDomain {
    return HomeDomain(
        profile = profile?.toDomain(),
        banners = banners?.map { it.toDomain() },
        recommendation = recommendation?.toDomain(),
        recent = recent?.toDomain()
    )
}