package com.example.chocokcakeV2.domain.auth.presentation.dto.request

import javax.validation.constraints.NotBlank

data class WithdrawalRequest(
    @field:NotBlank
    val password: String
)
