package com.example.chocokcakeV2.global.error.exception

import com.example.chocokcakeV2.global.error.data.ErrorCode

class UnAuthorizedException(data: String):BusinessException(ErrorCode.UN_AUTHORIZED, data)