package br.com.hellodev.common.data.mapping.banner

import br.com.hellodev.common.data.model.banner.BannerResponse
import br.com.hellodev.common.domain.model.banner.BannerDomain

fun BannerResponse.toDomain(): BannerDomain {
    return BannerDomain(
        id = id,
        image = image,
        url = url
    )
}