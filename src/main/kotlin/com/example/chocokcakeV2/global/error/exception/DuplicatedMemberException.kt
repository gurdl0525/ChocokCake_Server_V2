package com.example.chocokcakeV2.global.error.exception

import com.example.chocokcakeV2.global.error.ErrorCode

class DuplicatedMemberException(data: String): BusinessException(ErrorCode.DUPLICATED_MEMBER, data) {
}