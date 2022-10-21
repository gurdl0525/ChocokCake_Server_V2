package com.example.chocokcakeV2.domain.cake.service

import com.example.chocokcakeV2.domain.auth.entity.user.General
import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.cake.repository.CakeRepository
import org.springframework.stereotype.Service

@Service
class CakeServiceImpl(
    private val cakeRepository: CakeRepository
): CakeService {
    override fun generateCake(user: User) {
        if(user is General){
            //cakeRepository.save()
        }
    }
}