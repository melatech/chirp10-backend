package com.melatech.chirp10.api.dto

import com.melatech.chirp10.domain.type.UserId


data class UserDto(
    val id: UserId,
    val email: String,
    val username: String,
    val hasVerifiedEmail: Boolean
)
