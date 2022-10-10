package com.example.chocokcakeV2.domain.auth.presentation.controller

import com.example.chocokcakeV2.domain.auth.presentation.dto.request.GeneralSignUpRequest
import com.example.chocokcakeV2.domain.auth.service.AuthService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
@Validated
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/general/sign-up")
    fun generalSignUp(
        @Valid
        @RequestBody
        request: GeneralSignUpRequest
    ){
        authService.generalSignUp(request)
    }
}