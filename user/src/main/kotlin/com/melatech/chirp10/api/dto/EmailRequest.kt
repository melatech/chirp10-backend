package com.melatech.chirp10.api.dto

import jakarta.validation.constraints.Email

data class EmailRequest(
    @field:Email
    val email: String,

)
