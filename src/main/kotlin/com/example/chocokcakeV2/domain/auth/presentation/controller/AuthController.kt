package com.example.chocokcakeV2.domain.auth.presentation.controller

import com.example.chocokcakeV2.domain.auth.presentation.dto.request.AdminSignUpRequest
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.GeneralSignUpRequest
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.LoginRequest
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.ReissueTokenRequest
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.TokenResponse
import com.example.chocokcakeV2.domain.auth.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
@Validated
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/signup/general")
    @ResponseStatus(HttpStatus.CREATED)
    fun generalSignUp(
        @Valid
        @RequestBody
        request: GeneralSignUpRequest
    ){
        authService.generalSignUp(request)
    }

    @PostMapping("/signup/admin")
    @ResponseStatus(HttpStatus.CREATED)
    fun adminSignUp(
        @Valid
        @RequestBody
        request: AdminSignUpRequest
    ){
        authService.admin(request)
    }

    @PostMapping("/login")
    fun login(
        @Valid
        @RequestBody
        request: LoginRequest
    ): TokenResponse{
        return authService.login(request)
    }

    @PostMapping("/re-issue")
    fun reissue(
        @RequestBody
        request: ReissueTokenRequest
    ): TokenResponse {
        return authService.reissue(request)
    }
}