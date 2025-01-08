package br.com.hellodev.common.data.mapping.tag

import br.com.hellodev.common.data.model.tag.TagResponse
import br.com.hellodev.common.domain.model.tag.TagDomain

fun TagResponse.toDomain(): TagDomain {
    return TagDomain(
        id = id,
        name = name
    )
}