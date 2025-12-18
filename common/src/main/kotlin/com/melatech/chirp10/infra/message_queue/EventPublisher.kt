package com.melatech.chirp10.infra.message_queue

import com.melatech.chirp10.domain.events.Chirp10Event
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class EventPublisher(
    val rabbitTemplate: RabbitTemplate
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun <T: Chirp10Event> publish(event: T){
        try {
            rabbitTemplate.convertAndSend(
                event.exchange,
                event.eventKey,
                event
            )
            logger.info("Successfully published event: ${event.eventKey}")

        }catch (e: Exception){
            logger.error("failed to publish ${event.eventKey} event", e)

        }
    }
}