package com.example.chocokcakeV2.domain.candle.exception

import com.example.chocokcakeV2.global.error.data.ErrorCode
import com.example.chocokcakeV2.global.error.exception.BusinessException

class NotYetBirthDayException(data: String): BusinessException(ErrorCode.NOT_YET_BIRTHDAY, data)