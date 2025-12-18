package com.melatech.chirp10.api.dto

import com.melatech.chirp10.domain.model.DeviceToken
import com.melatech.chirp10.domain.type.UserId
import java.time.Instant

data class DeviceTokenDto(
    val userId: UserId,
    val token: String,
    val createdAt: Instant
)
