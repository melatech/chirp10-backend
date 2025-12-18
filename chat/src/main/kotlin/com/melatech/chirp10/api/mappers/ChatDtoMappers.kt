package com.melatech.chirp10.api.mappers

import com.melatech.chirp10.api.dto.ChatDto
import com.melatech.chirp10.api.dto.ChatMessageDto
import com.melatech.chirp10.api.dto.ChatParticipantDto
import com.melatech.chirp10.domain.models.Chat
import com.melatech.chirp10.domain.models.ChatMessage
import com.melatech.chirp10.domain.models.ChatParticipant



fun Chat.toChatDto(): ChatDto{
    return ChatDto(
        id = id,
        participants = participants.map {
            it.toChatParticipantDto()
        },
        lastActivityAt = lastActivityAt,
        lastMessage = lastMessage?.toChatMessageDto(),
        creator = creator.toChatParticipantDto()
    )
}

fun ChatMessage.toChatMessageDto(): ChatMessageDto {
    return ChatMessageDto(
        id = id,
        chatId = chatId,
        content = content,
        createdAt = createdAt,
        senderId = sender.userId
    )
}


fun ChatParticipant.toChatParticipantDto(): ChatParticipantDto {
    return ChatParticipantDto(
        userId = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl
    )
}