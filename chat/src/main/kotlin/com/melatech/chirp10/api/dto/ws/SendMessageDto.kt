package com.melatech.chirp10.api.dto.ws

import com.melatech.chirp10.domain.type.ChatId
import com.melatech.chirp10.domain.type.ChatMessageId

data class SendMessageDto(
    val chatId: ChatId,
    val content: String,
    val messageId: ChatMessageId? = null,
)
