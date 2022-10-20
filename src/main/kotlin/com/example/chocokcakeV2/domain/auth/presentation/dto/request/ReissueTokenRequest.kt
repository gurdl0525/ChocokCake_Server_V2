package com.example.chocokcakeV2.domain.auth.presentation.dto.request

data class ReissueTokenRequest(
    val accessToken: String,
    val refreshToken: String
)
