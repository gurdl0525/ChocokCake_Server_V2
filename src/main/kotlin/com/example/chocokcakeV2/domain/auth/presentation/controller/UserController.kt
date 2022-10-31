package com.example.chocokcakeV2.domain.auth.presentation.controller

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.UserMaximumInfoResponse
import com.example.chocokcakeV2.domain.auth.service.UserService
import com.example.chocokcakeV2.global.error.exception.TokenCanNotBeNullException
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/info/{id}")
    fun getMaximumMyInfo(
        @AuthenticationPrincipal user: User?,
        @PathVariable("id") id : Long
    ): UserMaximumInfoResponse {
        return userService.getUserInfo(user
            ?: throw TokenCanNotBeNullException(), id)
    }
}