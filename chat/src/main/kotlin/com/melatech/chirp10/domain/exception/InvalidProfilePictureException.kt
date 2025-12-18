package com.melatech.chirp10.domain.exception

class InvalidProfilePictureException(
    override val message: String? = null
): RuntimeException(
    message ?: "Invalid profile picture data"
) {

}