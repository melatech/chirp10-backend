package com.melatech.chirp10.api.dto

import com.melatech.chirp10.domain.type.UserId

data class ChatParticipantDto(
    val userId: UserId,
    val username: String,
    val email: String,
    val profilePictureUrl: String?
)
