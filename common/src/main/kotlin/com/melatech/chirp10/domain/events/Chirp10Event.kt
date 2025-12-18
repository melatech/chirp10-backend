package com.melatech.chirp10.domain.events

import java.time.Instant

interface Chirp10Event {
    val eventId: String
    val eventKey: String
    val occurredAt: Instant
    val exchange: String
}