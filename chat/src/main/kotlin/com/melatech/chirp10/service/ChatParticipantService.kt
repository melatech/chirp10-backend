package com.melatech.chirp10.service

import com.melatech.chirp10.domain.models.ChatParticipant
import com.melatech.chirp10.domain.type.UserId
import com.melatech.chirp10.infra.database.mappers.toChatParticipant
import com.melatech.chirp10.infra.database.mappers.toChatParticipantEntity
import com.melatech.chirp10.infra.database.repositories.ChatParticipantRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ChatParticipantService(
    private val chatParticipantRepository: ChatParticipantRepository
) {
    fun createChatParticipant(
        chatParticipant: ChatParticipant
    ){
        chatParticipantRepository.save(
            chatParticipant.toChatParticipantEntity()
        )
    }

    fun findChatParticipantById(userId: UserId): ChatParticipant? {
        return chatParticipantRepository.findByIdOrNull(userId)?.toChatParticipant()
    }

    fun findChatParticipantByEmailOrUsername(
        query: String
    ): ChatParticipant? {
        val normalizedQuery = query.lowercase().trim()
        return chatParticipantRepository.findByEmailOrUsername(
            query = normalizedQuery
        )?.toChatParticipant()

    }
}