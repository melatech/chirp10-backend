package com.melatech.chirp10.service

import com.melatech.chirp10.domain.type.ChatId
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Component

@Component
class MessageCacheEvictionHelper {
    @CacheEvict(
        value = ["messages"],
        key = "#chatId",
    )
    fun evictMessagesCache(chatId: ChatId) {
        // NO-OP: Let Spring handle the cache evict
    }
}