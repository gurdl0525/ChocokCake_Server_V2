package com.example.chocokcakeV2.domain.auth.service

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.*
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.TokenResponse

interface AuthService {

    fun checkDuplicateAccountId(request: String)

    fun generalSignUp(request: GeneralSignUpRequest)

    fun admin(request: AdminSignUpRequest)

    fun login(request: LoginRequest): TokenResponse

    fun reissue(request: ReissueTokenRequest): TokenResponse

    fun deleteMember(user: User, request: WithdrawalRequest)
}