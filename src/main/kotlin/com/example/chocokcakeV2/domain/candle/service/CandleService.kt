package com.example.chocokcakeV2.domain.candle.service

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.candle.presentation.dto.request.CreateCandleRequest
import com.example.chocokcakeV2.domain.candle.presentation.dto.response.MaximumCandleResponse

interface CandleService {

    fun createCandle(id: Long, request: CreateCandleRequest)

    fun getMaximumCandle(user: User, id: Long): MaximumCandleResponse

}