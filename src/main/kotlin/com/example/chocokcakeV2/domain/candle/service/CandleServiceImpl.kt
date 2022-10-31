package com.example.chocokcakeV2.domain.candle.service

import com.example.chocokcakeV2.domain.auth.entity.user.Admin
import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.cake.repository.CakeRepository
import com.example.chocokcakeV2.domain.candle.entity.Candle
import com.example.chocokcakeV2.domain.candle.exception.CandleNotFoundException
import com.example.chocokcakeV2.domain.candle.presentation.dto.request.CreateCandleRequest
import com.example.chocokcakeV2.domain.candle.presentation.dto.response.MaximumCandleResponse
import com.example.chocokcakeV2.domain.candle.repository.CandleRepository
import com.example.chocokcakeV2.global.error.exception.CakeNotFoundException
import com.example.chocokcakeV2.global.error.exception.NoPermissionsException
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

    override fun getMaximumCandle(user: User, id: Long): MaximumCandleResponse {
        val candle = candleRepository.findByIdOrNull(id)
            ?: throw CandleNotFoundException(id.toString())
        if(candle.cake.user != user && user !is Admin){
            throw NoPermissionsException(user.role.toString())
        }
        return MaximumCandleResponse(
            id = candle.id!!,
            theme = candle.candleTheme,
            postman = candle.postman,
            letter = candle.letter
        )
    }

}