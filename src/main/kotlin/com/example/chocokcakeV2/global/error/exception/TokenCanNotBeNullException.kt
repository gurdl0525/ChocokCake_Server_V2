package com.example.chocokcakeV2.global.error.exception

import com.example.chocokcakeV2.global.error.data.ErrorCode

class TokenCanNotBeNullException(data: String) : BusinessException(ErrorCode.TOKEN_CAN_NOT_BE_NULL, data) {
}
