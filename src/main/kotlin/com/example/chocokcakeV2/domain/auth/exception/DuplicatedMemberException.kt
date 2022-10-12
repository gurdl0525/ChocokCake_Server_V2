package com.example.chocokcakeV2.domain.auth.exception

import com.example.chocokcakeV2.global.error.data.ErrorCode
import com.example.chocokcakeV2.global.error.exception.BusinessException

class DuplicatedMemberException(data: String): BusinessException(ErrorCode.DUPLICATED_MEMBER, data) {
}