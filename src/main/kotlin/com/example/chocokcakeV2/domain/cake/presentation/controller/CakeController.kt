package com.example.chocokcakeV2.domain.cake.presentation.controller

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.cake.presentation.dto.ThemeRequest
import com.example.chocokcakeV2.domain.cake.service.CakeService
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cake")
class CakeController(
    private val cakeService: CakeService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun generateCake(
        @AuthenticationPrincipal user: User,
        @RequestBody request: ThemeRequest
    ){
        cakeService.generateCake(user, request)
    }
}