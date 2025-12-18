package com.melatech.chirp10.domain.exception

class UnauthorizedException: RuntimeException(
    "Missing auth details"
)