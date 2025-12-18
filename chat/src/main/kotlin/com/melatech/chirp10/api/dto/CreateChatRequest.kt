package com.melatech.chirp10.api.dto

import com.melatech.chirp10.domain.type.UserId
import jakarta.validation.constraints.Size


data class CreateChatRequest(

    @field:Size(
        min = 1,
        message = "Chats must have a least 2 unique participants"
        )
    val otherUserIds: List<UserId>
)
