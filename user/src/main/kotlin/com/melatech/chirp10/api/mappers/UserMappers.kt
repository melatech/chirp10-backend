package com.melatech.chirp10.api.mappers

import com.melatech.chirp10.api.dto.AuthenticatedUserDto
import com.melatech.chirp10.api.dto.UserDto
import com.melatech.chirp10.domain.model.AuthenticatedUser
import com.melatech.chirp10.domain.model.User

fun AuthenticatedUser.toAuthenticatedUserDto(): AuthenticatedUserDto {
    return AuthenticatedUserDto(
        user = user.toUserDto(),
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        email = email,
        username = username,
        hasVerifiedEmail = hasEmailVerified
    )
}