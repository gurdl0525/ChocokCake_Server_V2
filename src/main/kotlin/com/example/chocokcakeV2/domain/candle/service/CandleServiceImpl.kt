package com.example.chocokcakeV2.domain.candle.service

import com.example.chocokcakeV2.domain.cake.repository.CakeRepository
import com.example.chocokcakeV2.domain.candle.entity.Candle
import com.example.chocokcakeV2.domain.candle.presentation.dto.request.CreateCandleRequest
import com.example.chocokcakeV2.domain.candle.repository.CandleRepository
import com.example.chocokcakeV2.global.error.exception.CakeNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CandleServiceImpl(
    private val candleRepository: CandleRepository,
    private val cakeRepository: CakeRepository
): CandleService {

    override fun createCandle(id: Long, request: CreateCandleRequest) {
        val cake = cakeRepository.findByIdOrNull(id)
            ?: throw CakeNotFoundException(id.toString())
        candleRepository.save(
            Candle(
                id = null,
                candleTheme = request.theme,
                postman = request.postman,
                letter = request.letter,
                cake = cake,
                createdAt = LocalDateTime.now(),
                updatedAt = null
            )
        )
    }

}