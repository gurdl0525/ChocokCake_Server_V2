package com.example.chocokcakeV2.domain.auth.presentation.dto.request

import java.time.LocalDate
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class GeneralSignUpRequest(
    @field:Size(min = 2, max = 4)
    val name: String,
    val accountId: String,
    @field:Pattern(regexp = "sf")
    val password: String,
    val birthDay: LocalDate
)
