package br.com.hellodev.common.data.mapping.category

import br.com.hellodev.common.data.model.category.CategoryResponse
import br.com.hellodev.common.domain.model.category.CategoryDomain

fun CategoryResponse.toDomain(): CategoryDomain {
    return CategoryDomain(
        id = id,
        name = title
    )
}