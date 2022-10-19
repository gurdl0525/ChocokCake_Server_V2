package com.example.chocokcakeV2.domain.auth.repository

import com.example.chocokcakeV2.domain.auth.entity.token.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository: JpaRepository<RefreshToken, String> {
}