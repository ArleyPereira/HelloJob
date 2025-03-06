package br.com.hellodev.data.mapping.user

import br.com.hellodev.data.model.user.UserResponse
import br.com.hellodev.domain.model.user.UserDomain

fun UserResponse.toDomain(): UserDomain {
    return UserDomain(
        id = id,
        name = name,
        email = email,
        photo = photo,
        token = token
    )
}