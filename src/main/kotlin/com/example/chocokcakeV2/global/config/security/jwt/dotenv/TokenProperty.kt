package com.example.chocokcakeV2.global.config.security.jwt.dotenv

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("jwt")
@ConstructorBinding
data class TokenProperty(
    val header: String,
    val secretKey: String,
    val accessExp: Long,
    val refreshExp: Long
)
