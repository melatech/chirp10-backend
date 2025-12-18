package com.melatech.chirp10.domain.event

import com.melatech.chirp10.domain.type.UserId

data class ProfilePictureUpdatedEvent(
    val userId: UserId,
    val newUrl: String?,
)
