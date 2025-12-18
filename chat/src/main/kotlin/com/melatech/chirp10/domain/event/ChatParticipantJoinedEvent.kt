package com.melatech.chirp10.domain.event

import com.melatech.chirp10.domain.type.ChatId
import com.melatech.chirp10.domain.type.UserId

data class ChatParticipantJoinedEvent(
    val chatId: ChatId,
    val userIds: Set<UserId>
)
