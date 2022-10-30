package com.example.chocokcakeV2.domain.cake.service

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.cake.presentation.dto.ThemeRequest

interface CakeService {
    fun generateCake(user: User, request: ThemeRequest)
}