package com.example.chocokcakeV2.domain.auth.presentation.controller

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.*
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.TokenResponse
import com.example.chocokcakeV2.domain.auth.service.AuthService
import com.example.chocokcakeV2.global.error.exception.TokenCanNotBeNullException
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/auth")
@Validated
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/check")
    fun checkDuplicateAccountId(
        @Valid @NotBlank
        @RequestParam(required = true)
        request: String
    ){
        authService.checkDuplicateAccountId(request)
    }

    @PostMapping("/general")
    @ResponseStatus(HttpStatus.CREATED)
    fun generalSignUp(
        @Valid
        @RequestBody
        request: GeneralSignUpRequest
    ){
        authService.generalSignUp(request)
    }

    @PostMapping("/admin")
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

    @DeleteMapping
    fun deleteMember(
        @Valid
        @RequestBody request: WithdrawalRequest,
        @AuthenticationPrincipal user: User?
    ){
        authService.deleteMember(user
            ?: throw TokenCanNotBeNullException(), request)
    }
}