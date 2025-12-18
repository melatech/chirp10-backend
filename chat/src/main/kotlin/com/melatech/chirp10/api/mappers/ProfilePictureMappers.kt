package com.melatech.chirp10.api.mappers

import com.melatech.chirp10.api.dto.PictureUploadResponse
import com.melatech.chirp10.domain.models.ProfilePictureUploadCredentials

fun ProfilePictureUploadCredentials.toResponse(): PictureUploadResponse{
    return PictureUploadResponse(
        uploadUrl = uploadUrl,
        publicUrl = publicUrl,
        headers = headers,
        expiresAt = expiresAt
    )

}