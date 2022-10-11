package com.example.chocokcakeV2.domain.auth.presentation.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern

data class GeneralSignUpRequest(
    val name: String,
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val accountId: String,
    @field:Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#\$%^&*()+|=]{8,30}\$",
        message = "비밀번호는 영소문자,대문자,숫자,특수문자 8~30자여야 합니다.")
    val password: String,
    @field:Min(1, message = "1월부터 12월까지 입력 가능합니다")
    @field:Max(12, message = "1월부터 12월까지 입력 가능합니다")
    val monthOfBirthDay: Int,
    @field:Min(1, message = "1일부터 31일까지 입력 가능합니다")
    @field:Max(31, message = "1일부터 31일까지 입력 가능합니다")
    val dayOfBirthDay: Int
)
