package com.example.chocokcakeV2.domain.cake.presentation.controller

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.cake.service.CakeService
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cake")
class CakeController(
    private val cakeService: CakeService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun generateCake(
        @AuthenticationPrincipal user: User
    ){
        cakeService.generateCake(user)
    }
}