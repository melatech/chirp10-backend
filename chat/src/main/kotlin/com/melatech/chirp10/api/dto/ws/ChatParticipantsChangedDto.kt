package com.melatech.chirp10.api.dto.ws

import com.melatech.chirp10.domain.type.ChatId

data class ChatParticipantsChangedDto(
    val chatId: ChatId
)