package com.example.chocokcakeV2.domain.candle.presentation.controller

import com.example.chocokcakeV2.domain.candle.presentation.dto.request.CreateCandleRequest
import com.example.chocokcakeV2.domain.candle.service.CandleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

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
}