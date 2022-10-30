package com.example.chocokcakeV2.domain.cake.presentation.dto.response

import com.example.chocokcakeV2.domain.cake.entity.type.CakeTheme
import com.example.chocokcakeV2.domain.candle.presentation.dto.response.MinimumCandleResponse
import org.springframework.data.domain.Page
import java.time.LocalDate

data class MaximumCakeResponse(
    val id: Long,
    val theme: CakeTheme,
    val birthDay: LocalDate,
    val userName: String,
    val candles: Page<MinimumCandleResponse>
)
