package com.example.chocokcakeV2.domain.auth.presentation.controller

import com.example.chocokcakeV2.domain.auth.presentation.dto.request.*
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.TokenResponse
import com.example.chocokcakeV2.domain.auth.service.AuthService
import com.example.chocokcakeV2.global.error.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import com.example.chocokcakeV2.domain.auth.entity.user.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @PostMapping("/check/id/{accountId}")
    fun checkDuplicateAccountId(
        @PathVariable("accountId", required = true)
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
        println(user)
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        println(authentication)
        println(authentication.principal.toString())
        authService.deleteMember(
            user?:throw UserNotFoundException("Not Found User By Token"),
            request
        )
    }
}