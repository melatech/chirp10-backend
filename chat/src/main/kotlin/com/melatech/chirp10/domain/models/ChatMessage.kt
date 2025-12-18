package com.melatech.chirp10.domain.models

import com.melatech.chirp10.domain.type.ChatId
import com.melatech.chirp10.domain.type.ChatMessageId
import java.time.Instant

data class ChatMessage(
    val id: ChatMessageId,
    val chatId: ChatId,
    val sender: ChatParticipant,
    val content: String,
    val createdAt: Instant
)
