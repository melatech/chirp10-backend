package com.melatech.chirp10.api.dto.ws

import com.melatech.chirp10.domain.type.UserId

data class ProfilePictureUpdateDto(
    val userId: UserId,
    val newUrl: String?
)
