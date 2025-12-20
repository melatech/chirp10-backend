package com.melatech.chirp10.domain.event

import com.melatech.chirp10.domain.type.ChatId
import com.melatech.chirp10.domain.type.UserId

data class ChatCreatedEvent(
    val chatId: ChatId,
    val participantIds: List<UserId>
)
