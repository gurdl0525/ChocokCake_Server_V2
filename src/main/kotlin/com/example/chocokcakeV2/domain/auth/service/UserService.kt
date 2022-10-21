package com.example.chocokcakeV2.domain.auth.service

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.UserMaximumInfoResponse

interface UserService {
    fun getUserInfo(user: User, id: Long): UserMaximumInfoResponse
}
