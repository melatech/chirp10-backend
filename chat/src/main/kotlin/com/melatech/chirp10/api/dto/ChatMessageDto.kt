package com.melatech.chirp10.api.dto

import com.melatech.chirp10.domain.type.ChatId
import com.melatech.chirp10.domain.type.ChatMessageId
import com.melatech.chirp10.domain.type.UserId
import java.time.Instant

data class ChatMessageDto(
    val id: ChatMessageId,
    val chatId: ChatId,
    val content: String,
    val createdAt: Instant,
    val senderId: UserId
    )
