package com.example.chocokcakeV2.global.error.exception

import com.example.chocokcakeV2.global.error.data.ErrorCode

class TokenCanNotBeNullException: BusinessException(ErrorCode.TOKEN_CAN_NOT_BE_NULL, "Token is Null")
