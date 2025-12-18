package com.melatech.chirp10.api.mappers

import com.melatech.chirp10.api.dto.DeviceTokenDto
import com.melatech.chirp10.api.dto.PlatformDto
import com.melatech.chirp10.domain.model.DeviceToken

fun DeviceToken.toDeviceTokenDto(): DeviceTokenDto {
    return DeviceTokenDto(
        userId = userId,
        token = token,
        createdAt = createdAt
    )
}

fun PlatformDto.toPlatformDto(): DeviceToken.Platform {
    return when(this){
        PlatformDto.ANDROID -> DeviceToken.Platform.ANDROID
        PlatformDto.IOS -> DeviceToken.Platform.IOS
    }
}