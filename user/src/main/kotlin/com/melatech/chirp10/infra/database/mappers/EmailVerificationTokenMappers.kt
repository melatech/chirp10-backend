package com.melatech.chirp10.infra.database.mappers

import com.melatech.chirp10.domain.model.EmailVerificationToken
import com.melatech.chirp10.infra.database.entities.EmailVerificationTokenEntity

fun EmailVerificationTokenEntity.toEmailVerificationToken(): EmailVerificationToken {
    return EmailVerificationToken(
        id = id,
        token = token,
        user = user.toUser()
    )
}