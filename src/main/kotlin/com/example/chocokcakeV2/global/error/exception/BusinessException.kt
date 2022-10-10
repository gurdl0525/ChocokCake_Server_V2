package com.example.chocokcakeV2.global.error.exception

import com.example.chocokcakeV2.global.error.ErrorCode

open class BusinessException(
    val errorCode: ErrorCode,
    val data: String
): RuntimeException(errorCode.message) {
}