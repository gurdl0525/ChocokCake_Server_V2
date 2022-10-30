package com.example.chocokcakeV2.domain.candle.presentation.dto.response

import com.example.chocokcakeV2.domain.cake.entity.Cake
import com.example.chocokcakeV2.domain.candle.entity.Candle
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CandleRepository: JpaRepository<Candle, Long> {

    fun findCandlesByCake(cake: Cake, pageable: Pageable): Page<Candle>
}