package com.melatech.chirp10.domain.exception

import com.melatech.chirp10.domain.type.UserId

class ChatParticipantNotFoundException(
    private val id: UserId
): RuntimeException(
    "The chat participant with the ID $id was not found."
) {

}