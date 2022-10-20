package com.example.chocokcakeV2.domain.auth.repository

import com.example.chocokcakeV2.domain.auth.entity.token.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RefreshTokenRepository: CrudRepository<RefreshToken, String> {
    fun findByAccessTokenAndRefreshToken(accessToken: String, refreshToken: String): Optional<RefreshToken>
}