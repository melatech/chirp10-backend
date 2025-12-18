package com.melatech.chirp10.infra.database.mappers

import com.melatech.chirp10.domain.model.User
import com.melatech.chirp10.infra.database.entities.UserEntity

fun UserEntity.toUser(): User {
    return User(
        id = id!!,
        username = username,
        email = email,
        hasEmailVerified = hasVerifiedEmail
    )
}