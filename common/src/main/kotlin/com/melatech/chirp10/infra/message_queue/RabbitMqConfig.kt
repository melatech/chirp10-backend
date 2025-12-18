package com.melatech.chirp10.infra.message_queue

//import com.tools.jackson.datatype.jsr310.JavaTimeModule
//import tools.jackson.datatype.jsr310.JavaTimeModule
import com.melatech.chirp10.domain.events.Chirp10Event
import com.melatech.chirp10.domain.events.chat.ChatEventConstants
import com.melatech.chirp10.domain.events.user.UserEventConstants
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
//import org.gradle.internal.impldep.com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import tools.jackson.databind.DefaultTyping
import tools.jackson.databind.JacksonModule
import tools.jackson.databind.json.JsonMapper
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import tools.jackson.module.kotlin.KotlinModule


@Configuration
@EnableTransactionManagement
class RabbitMqConfig {

    @Bean
    fun messageConverter(): org.springframework.amqp.support.converter.JacksonJsonMessageConverter {

        val ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfBaseType(Chirp10Event::class.java)
            .allowIfSubType("java.util.")
            .allowIfSubType("kotlin.collections.")
            .allowIfSubType("com.melatech.chirp10.domain.events.user")
            //.allowIfSubType("com.melatech.chirp10.")
            .build()

        val mapper = JsonMapper.builder()
            .addModule(KotlinModule.Builder().build())
            //.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            //.addModule(JavaTimeModule())
            //.addModule(JavaTimeModule())
            //.registerModule(JavaTimeModule())
            .polymorphicTypeValidator(ptv)
            .activateDefaultTyping(ptv, DefaultTyping.NON_FINAL)
            .build()

        return org.springframework.amqp.support.converter.JacksonJsonMessageConverter(mapper)
    }

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: org.springframework.amqp.support.converter.JacksonJsonMessageConverter,
        //messageConverter: JacksonJsonMessageConverter,
    ): RabbitTemplate {
        return RabbitTemplate(connectionFactory).apply {
            this.messageConverter = messageConverter
        }
    }

    @Bean
    fun userExchange() = TopicExchange(
        UserEventConstants.USER_EXCHANGE,
        true,
        false
    )

    @Bean
    fun chatExchange() = TopicExchange(
        ChatEventConstants.CHAT_EXCHANGE,
        true,
        false
    )

    @Bean
    fun chatUserEventsQueue() = Queue(
        MessageQueues.CHAT_USER_EVENTS,
        true
    )

    @Bean
    fun notificationUserEventsQueue() = Queue(
        MessageQueues.NOTIFICATION_USER_EVENTS,
        true
    )

    @Bean
    fun notificationChatEventsQueue() = Queue(
        MessageQueues.NOTIFICATION_CHAT_EVENTS,
        true
    )

    @Bean
    fun notificationChatEventsBinding(
        notificationChatEventsQueue: Queue,
        chatExchange: TopicExchange,
    ): Binding {

        return BindingBuilder
            .bind(notificationChatEventsQueue)
            .to(chatExchange)
            .with(ChatEventConstants.CHAT_NEW_MESSAGE)
    }

    @Bean
    fun notificationUserEventsBinding(
        notificationUserEventsQueue: Queue,
        userExchange: TopicExchange,
    ): Binding {

        return BindingBuilder
            .bind(notificationUserEventsQueue)
            .to(userExchange)
            .with("user.*")
    }

    @Bean
    fun chatUserEventsBinding(
        chatUserEventsQueue: Queue,
        userExchange: TopicExchange
    ): Binding {

        return BindingBuilder
            .bind(chatUserEventsQueue)
            .to(userExchange)
            .with("user.*")
    }
}




