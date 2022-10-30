package com.example.chocokcakeV2.domain.cake.exception

import com.example.chocokcakeV2.global.error.data.ErrorCode
import com.example.chocokcakeV2.global.error.exception.BusinessException

class AlreadyExistCakeException(data: String) : BusinessException(ErrorCode.ALREADY_EXIST_CAKE, data) {
}