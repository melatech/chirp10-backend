package com.melatech.chirp10.infra.caching

import com.melatech.chirp10.domain.events.Chirp10Event
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import tools.jackson.databind.DefaultTyping
import tools.jackson.databind.json.JsonMapper
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import tools.jackson.module.kotlin.KotlinModule
import java.time.Duration

@Configuration
@EnableCaching
class RedisConfig {

    @Bean
    fun cacheManager(
        //connectionFactory: ConnectionFactory,
        redisConnectionFactory: RedisConnectionFactory
    ): RedisCacheManager {

        val ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfBaseType(Chirp10Event::class.java)
            .allowIfSubType("java.util.")
            .allowIfSubType("kotlin.collections.")
            .allowIfSubType("com.melatech.chirp10.")
            //.allowIfSubType("com.melatech.chirp10.domain.events.user")
            .build()

        val mapper = JsonMapper.builder()
            .addModule(KotlinModule.Builder().build())

            .polymorphicTypeValidator(ptv)
            .activateDefaultTyping(ptv, DefaultTyping.NON_FINAL)
            .build()

        //return org.springframework.amqp.support.converter.JacksonJsonMessageConverter(mapper)

        //val serializer = GenericJackson2JsonRedisSerializer(mapper)
        val serializer = GenericJacksonJsonRedisSerializer(mapper)

        val config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofHours(1L))
            //.serializeValuesWith(
            //    RedisSerializationContext.SerializationPair.fromSerializer(serializer))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(serializer)
            )


        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(config)
            .withCacheConfiguration(
                "messages",
                config.entryTtl(Duration.ofMinutes(30))
            )
            .transactionAware()
            .build()


    }
}