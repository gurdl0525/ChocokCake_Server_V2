package com.example.chocokcakeV2.domain.candle.service

import com.example.chocokcakeV2.domain.candle.presentation.dto.request.CreateCandleRequest

interface CandleService {

    fun createCandle(id: Long, request: CreateCandleRequest)

}