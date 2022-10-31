package com.example.chocokcakeV2.domain.candle.presentation.dto.request

import com.example.chocokcakeV2.domain.candle.entity.type.CandleTheme

data class CreateCandleRequest(
    val theme: CandleTheme,
    val postman: String?,
    val letter: String
)
