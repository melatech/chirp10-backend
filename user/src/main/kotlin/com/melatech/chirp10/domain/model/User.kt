package com.melatech.chirp10.domain.model

import com.melatech.chirp10.domain.type.UserId

data class User(
    val id: UserId,
    val username: String,
    val email: String,
    val hasEmailVerified: Boolean
)
