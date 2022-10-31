package com.example.chocokcakeV2.domain.candle.exception

import com.example.chocokcakeV2.global.error.data.ErrorCode
import com.example.chocokcakeV2.global.error.exception.BusinessException

class CandleNotFoundException(data: String) : BusinessException(ErrorCode.CANDLE_NOT_FOUND, data) {
}
