package com.example.chocokcakeV2.domain.auth.entity.token

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import javax.persistence.Id

@RedisHash
class RefreshToken(
    id: String,
    accessToken: String,
    refreshToken: String,
    refreshExp: Long
) {
    @Id
    val id: String = id

    @Indexed
    var accessToken: String = accessToken
        protected set

    @Indexed
    var refreshToken: String = refreshToken
        protected set

    @TimeToLive
    var ttl: Long = refreshExp
        protected set

    fun reset(accessToken: String,refreshToken: String, refreshExp: Long){
        this.accessToken = accessToken
        this.refreshToken = refreshToken
        this.ttl = refreshExp
    }

}