package com.example.chocokcakeV2.domain.cake.service

import com.example.chocokcakeV2.domain.auth.entity.user.General
import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.cake.entity.Cake
import com.example.chocokcakeV2.domain.cake.exception.AlreadyExistCakeException
import com.example.chocokcakeV2.domain.cake.presentation.dto.request.ThemeRequest
import com.example.chocokcakeV2.domain.cake.presentation.dto.response.MaximumCakeResponse
import com.example.chocokcakeV2.domain.cake.repository.CakeRepository
import com.example.chocokcakeV2.domain.candle.presentation.dto.response.CandleRepository
import com.example.chocokcakeV2.domain.candle.presentation.dto.response.MinimumCandleResponse
import com.example.chocokcakeV2.global.common.facade.BirthDayFacade
import com.example.chocokcakeV2.global.error.exception.CakeNotFoundException
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class CakeServiceImpl(
    private val cakeRepository: CakeRepository,
    private val birthDayFacade: BirthDayFacade,
    private val candleRepository: CandleRepository
): CakeService {

    override fun generateCake(user: User, request: ThemeRequest) {
        if(user is General){
            val birthDay = user.birthDay!!
            if(birthDay.isBefore(LocalDate.now())){
                user.setBirthday(
                    birthDayFacade.enterBirthDay(
                        birthDay.month.value,
                        birthDay.dayOfMonth
                    )
                )
            }
            val cakeList = user.cakeList
            if(cakeList.size -1 >= 0 && (cakeList[cakeList.size -1].birthDay == user.birthDay)){
                throw AlreadyExistCakeException(user.toString())
            }
            cakeRepository.save(Cake(
                id = null,
                cakeTheme = request.theme,
                birthDay = user.birthDay!!,
                user = user,
                createdAt = LocalDateTime.now(),
                updatedAt = null
            ))
        }
    }

    override fun getMaximumCake(id: Long, page: Int): MaximumCakeResponse {
        val cake = cakeRepository.findByIdOrNull(id)
            ?: throw CakeNotFoundException(id.toString())
        return MaximumCakeResponse(
            id = id,
            theme = cake.cakeTheme,
            birthDay = cake.birthDay,
            userName = cake.user.name,
            candles = candleRepository.findCandlesByCake(cake, PageRequest.of(page, 8))
                .map {
                    MinimumCandleResponse(
                        id = it.id!!,
                        theme = it.candleTheme,
                        postman = it.postman
                    )
                }
        )
    }

}