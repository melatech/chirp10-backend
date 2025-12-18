package com.melatech.chirp10.api.controllers

import com.melatech.chirp10.api.util.requestUserId
import com.melatech.chirp10.domain.type.ChatMessageId
import com.melatech.chirp10.service.ChatMessageService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/messages")
class ChatMessageController(
    private val chatMessageService: ChatMessageService
) {

    @DeleteMapping("/{messageId}")
    fun deleteMessage(
        @PathVariable("messageId") messageId: ChatMessageId
    ){
        chatMessageService.deleteMessage(messageId, requestUserId)
    }

}