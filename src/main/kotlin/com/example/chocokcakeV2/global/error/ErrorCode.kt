package com.example.chocokcakeV2.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val message: String,
    val status: HttpStatus
) {
    //400
    DUPLICATED_MEMBER("Duplicated Member", HttpStatus.BAD_REQUEST)
}