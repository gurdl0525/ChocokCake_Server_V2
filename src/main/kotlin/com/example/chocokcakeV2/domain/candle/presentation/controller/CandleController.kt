package com.example.chocokcakeV2.domain.candle.presentation.controller

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.candle.presentation.dto.request.CreateCandleRequest
import com.example.chocokcakeV2.domain.candle.presentation.dto.response.MaximumCandleResponse
import com.example.chocokcakeV2.domain.candle.service.CandleService
import com.example.chocokcakeV2.global.error.exception.TokenCanNotBeNullException
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/candle")
class CandleController(
    private val candleService: CandleService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCandle(
        @RequestParam id: Long,
        @RequestBody request: CreateCandleRequest
    ){
        candleService.createCandle(id, request)
    }

    @GetMapping
    fun getMaximumCandle(
        @AuthenticationPrincipal user: User?,
        @RequestParam id: Long
    ): MaximumCandleResponse{
        return candleService.getMaximumCandle(user
            ?: throw TokenCanNotBeNullException(), id)
    }
}