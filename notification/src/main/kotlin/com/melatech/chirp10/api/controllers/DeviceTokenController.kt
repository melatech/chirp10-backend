package com.melatech.chirp10.api.controllers

import com.melatech.chirp10.api.dto.DeviceTokenDto
import com.melatech.chirp10.api.dto.RegisterDeviceRequest
import com.melatech.chirp10.api.mappers.toDeviceTokenDto
import com.melatech.chirp10.api.mappers.toPlatformDto
import com.melatech.chirp10.api.util.requestUserId
import com.melatech.chirp10.domain.model.DeviceToken
import com.melatech.chirp10.service.PushNotificationService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/notification")
class DeviceTokenController(
    private val pushNotificationService: PushNotificationService
) {
    @PostMapping("/register")
    fun registerDeviceToken(
        @Valid @RequestBody body: RegisterDeviceRequest
    ): DeviceTokenDto{

        return pushNotificationService.registerDevice(
            userId = requestUserId,
            token = body.token,
            platform = body.platform.toPlatformDto()
        ).toDeviceTokenDto()
    }

    @DeleteMapping("{token}")
    fun unregisterDeviceToken(
        @PathVariable( "token") token: String
    ){
        pushNotificationService.unregisterDevice(token)

    }
}