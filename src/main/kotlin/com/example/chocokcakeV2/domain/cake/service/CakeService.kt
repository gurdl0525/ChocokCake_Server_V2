package com.example.chocokcakeV2.domain.cake.service

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.cake.presentation.dto.request.ThemeRequest
import com.example.chocokcakeV2.domain.cake.presentation.dto.response.MaximumCakeResponse

interface CakeService {
    fun generateCake(user: User, request: ThemeRequest)

    fun getMaximumCake(id: Long, page: Int): MaximumCakeResponse

}