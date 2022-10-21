package com.example.chocokcakeV2.global.error.exception

import com.example.chocokcakeV2.global.error.data.ErrorCode

class NoPermissionsException(data: String) : BusinessException(ErrorCode.NO_PERMISSIONS, data)
