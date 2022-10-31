package com.example.chocokcakeV2.global.error.exception

import com.example.chocokcakeV2.global.error.data.ErrorCode

class UserNotFoundException(data: String): BusinessException(ErrorCode.USER_NOT_FOUND, data)