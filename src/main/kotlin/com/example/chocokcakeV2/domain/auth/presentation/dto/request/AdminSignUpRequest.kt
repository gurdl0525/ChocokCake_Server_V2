package com.example.chocokcakeV2.domain.auth.presentation.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

data class AdminSignUpRequest(
    val name: String,
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val accountId: String,
    @field:Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#\$%^&*()+|=]{8,30}\$",
        message = "비밀번호는 영소문자,대문자,숫자,특수문자 8~30자여야 합니다.")
    val password: String
)
