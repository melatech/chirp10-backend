package com.melatech.chirp10.api.dto

import jakarta.validation.constraints.NotBlank

data class ChangePasswordRequest(
   @field:NotBlank
    val oldPassword: String,
   @field:NotBlank
    val newPassword: String
)
