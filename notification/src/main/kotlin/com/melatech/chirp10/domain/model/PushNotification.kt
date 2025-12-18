package com.melatech.chirp10.domain.model

import com.melatech.chirp10.domain.type.ChatId
import java.util.UUID

data class PushNotification(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val recipients: List<DeviceToken>,
    val message: String,
    val chatId: ChatId,
    val data: Map<String, String>
    )
