package com.melatech.chirp10.api.dto

import com.melatech.chirp10.domain.models.ChatParticipant
import com.melatech.chirp10.domain.type.ChatId
import java.time.Instant

data class ChatDto(
    val id: ChatId,
    val participants: List<ChatParticipantDto>,
    val lastActivityAt: Instant,
    val lastMessage: ChatMessageDto?,
    val creator: ChatParticipantDto
)
