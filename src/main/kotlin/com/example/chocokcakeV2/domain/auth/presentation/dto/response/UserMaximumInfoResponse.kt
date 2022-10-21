package com.example.chocokcakeV2.domain.auth.presentation.dto.response

data class UserMaximumInfoResponse(
    val id: Long,
    val name: String,
    val accountId: String,
    val monthOfBirthDay: Int?,
    val dayOfBirthDay: Int?
)
