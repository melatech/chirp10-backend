package com.melatech.chirp10.infra.mappers

import com.melatech.chirp10.domain.model.DeviceToken
import com.melatech.chirp10.infra.database.DeviceTokenEntity

fun DeviceTokenEntity.toDeviceToken(): DeviceToken {
    return DeviceToken(
        userId = userId,
        token = token,
        platform = platform.toPlatform(),
        createdAt = createdAt,
        id = id
    )
}