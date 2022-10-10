package com.example.chocokcakeV2.domain.auth.service

import com.example.chocokcakeV2.domain.auth.presentation.dto.request.GeneralSignUpRequest

interface AuthService {
    fun generalSignUp(request: GeneralSignUpRequest)
}