package com.example.chocokcakeV2.global.error.exception

import com.example.chocokcakeV2.global.error.data.ErrorCode

class CakeNotFoundException(data: String): BusinessException(ErrorCode.CAKE_NOT_FOUND, data) {
}