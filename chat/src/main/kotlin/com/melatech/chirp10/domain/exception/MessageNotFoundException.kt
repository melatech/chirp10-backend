package com.melatech.chirp10.domain.exception

import com.melatech.chirp10.domain.type.ChatMessageId

class MessageNotFoundException(
    private val id: ChatMessageId
): RuntimeException(
    "Message with ID $id not found")