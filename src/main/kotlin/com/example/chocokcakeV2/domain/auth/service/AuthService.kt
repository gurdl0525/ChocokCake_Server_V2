package com.example.chocokcakeV2.domain.auth.service

import com.example.chocokcakeV2.domain.auth.presentation.dto.request.AdminSignUpRequest
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.GeneralSignUpRequest
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.LoginRequest
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.ReissueTokenRequest
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.TokenResponse

interface AuthService {
    fun generalSignUp(request: GeneralSignUpRequest)
    fun admin(request: AdminSignUpRequest)
    fun login(request: LoginRequest): TokenResponse
    fun reissue(request: ReissueTokenRequest): TokenResponse
}