package com.melatech.chirp10.api.dto.ws

import com.melatech.chirp10.domain.type.ChatId
import com.melatech.chirp10.domain.type.ChatMessageId

data class DeleteMessageDto(
    val chatId: ChatId,
    val messageId: ChatMessageId
)
