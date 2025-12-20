package com.melatech.chirp10.service

import com.melatech.chirp10.domain.event.MessageDeletedEvent
import com.melatech.chirp10.domain.events.chat.ChatEvent
import com.melatech.chirp10.domain.exception.ChatNotFoundException
import com.melatech.chirp10.domain.exception.ChatParticipantNotFoundException
import com.melatech.chirp10.domain.exception.ForbiddenException
import com.melatech.chirp10.domain.exception.MessageNotFoundException
import com.melatech.chirp10.domain.models.ChatMessage
import com.melatech.chirp10.domain.type.ChatId
import com.melatech.chirp10.domain.type.ChatMessageId
import com.melatech.chirp10.domain.type.UserId
import com.melatech.chirp10.infra.database.entities.ChatMessageEntity
import com.melatech.chirp10.infra.database.mappers.toChatMessage
import com.melatech.chirp10.infra.database.repositories.ChatMessageRepository
import com.melatech.chirp10.infra.database.repositories.ChatParticipantRepository
import com.melatech.chirp10.infra.database.repositories.ChatRepository
import com.melatech.chirp10.infra.message_queue.EventPublisher
import org.springframework.cache.annotation.CacheEvict
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ChatMessageService(
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val eventPublisher: EventPublisher,
    private val messageCacheEvictionHelper: MessageCacheEvictionHelper,
) {

    @Transactional
    @CacheEvict(
        value = ["messages"],
        key = "#chatId",
    )
    fun sendMessage(
        chatId: ChatId,
        senderId: UserId,
        content: String,
        messageId: ChatMessageId? = null,
    ): ChatMessage {
        val chat = chatRepository.findChatById(chatId, senderId)
            ?: throw ChatNotFoundException()
        val sender = chatParticipantRepository.findByIdOrNull(senderId)
            ?: throw ChatParticipantNotFoundException(senderId)

        val savedMessage = chatMessageRepository.saveAndFlush(
            ChatMessageEntity(
                id = messageId ?: UUID.randomUUID(),
                content = content.trim(),
                chatId = chatId,
                chat = chat,
                sender = sender
            )
        )

        eventPublisher.publish(
            event = ChatEvent.NewMessage(
                senderId = sender.userId,
                senderUsername = sender.username,
                recipientIds = chat.participants.map { it.userId }.toSet(),
                chatId = chatId,
                message = savedMessage.content

            )
        )

        return savedMessage.toChatMessage()
    }


    @Transactional
    fun deleteMessage(
        messageId: ChatMessageId,
        requestUserId: UserId,
    ) {
        val message = chatMessageRepository.findByIdOrNull(messageId)
            ?: throw MessageNotFoundException(messageId)

        if (message.sender.userId != requestUserId) {
            throw ForbiddenException()

        }

        chatMessageRepository.delete(message)

        applicationEventPublisher.publishEvent(
            MessageDeletedEvent(
                chatId = message.chatId,
                messageId = messageId
            )
        )

        messageCacheEvictionHelper.evictMessagesCache(message.chatId)
    }
}