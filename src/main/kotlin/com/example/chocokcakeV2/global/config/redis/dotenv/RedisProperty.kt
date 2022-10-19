package com.example.chocokcakeV2.global.config.redis.dotenv

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("spring.redis")
@ConstructorBinding
data class RedisProperty(
    val port: Int,
    val host: String
)
