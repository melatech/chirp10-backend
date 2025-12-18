package com.melatech.chirp10.api.util

import com.melatech.chirp10.domain.exception.UnauthorizedException
import com.melatech.chirp10.domain.type.UserId
import org.springframework.security.core.context.SecurityContextHolder

val requestUserId: UserId
 get() = (SecurityContextHolder.getContext().authentication?.principal as? UserId
       ?: throw UnauthorizedException()
         )





