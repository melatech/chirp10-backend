package com.melatech.chirp10.domain.models

import com.melatech.chirp10.domain.type.UserId

data class ChatParticipant(
    val userId: UserId,
    val username: String,
    val email: String,
    val profilePictureUrl: String?

)
