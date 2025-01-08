package br.com.hellodev.common.data.mapping.user

import br.com.hellodev.common.data.model.user.UserResponse
import br.com.hellodev.common.domain.model.user.UserDomain

fun UserResponse.toDomain(): UserDomain {
    return UserDomain(
        id = id,
        name = name,
        email = email,
        photo = photo,
        token = token
    )
}