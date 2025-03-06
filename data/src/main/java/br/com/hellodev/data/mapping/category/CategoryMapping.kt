package br.com.hellodev.data.mapping.category

import br.com.hellodev.data.model.category.CategoryResponse
import br.com.hellodev.domain.model.category.CategoryDomain

fun CategoryResponse.toDomain(): CategoryDomain {
    return CategoryDomain(
        id = id,
        name = title
    )
}