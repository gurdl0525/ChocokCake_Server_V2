package com.example.chocokcakeV2.domain.candle.presentation.dto.response

import com.example.chocokcakeV2.domain.candle.entity.type.CandleTheme

data class MaximumCandleResponse(
    val id: Long,
    val theme: CandleTheme,
    val postman: String?,
    val letter: String
)
