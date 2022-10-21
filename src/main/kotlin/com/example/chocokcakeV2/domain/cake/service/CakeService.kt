package com.example.chocokcakeV2.domain.cake.service

import com.example.chocokcakeV2.domain.auth.entity.user.User

interface CakeService {
    fun generateCake(user: User)
}