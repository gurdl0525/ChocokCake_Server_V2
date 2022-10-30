package com.example.chocokcakeV2.global.common.facade

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class BirthDayFacade {
    fun enterBirthDay(month: Int, day: Int): LocalDate {
        var birthDay = LocalDate.of(LocalDate.now().year, month, day)
        if(birthDay.isBefore(LocalDate.now())){
            birthDay = birthDay.plusYears(1)
        }
        return birthDay
    }
}