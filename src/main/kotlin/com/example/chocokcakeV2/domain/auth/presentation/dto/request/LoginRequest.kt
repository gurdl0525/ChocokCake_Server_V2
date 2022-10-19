package com.example.chocokcakeV2.domain.auth.presentation.dto.request

import javax.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank(message = "공백이 포함됐거나 값이 null값입니다")
    val accountId: String,
    @field:NotBlank(message = "공백이 포함됐거나 값이 null값입니다")
    val password: String
)
