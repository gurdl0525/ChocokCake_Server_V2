package com.example.chocokcakeV2.domain.auth.entity.token

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import javax.persistence.Id

@RedisHash(timeToLive = 723900)
data class RefreshToken(

    @Id
    val id: String,

    @Indexed
    val accessToken: String,

    @Indexed
    val refreshToken: String
)