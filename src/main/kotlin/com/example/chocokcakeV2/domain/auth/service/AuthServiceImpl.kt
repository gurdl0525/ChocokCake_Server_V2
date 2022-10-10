package com.example.chocokcakeV2.domain.auth.service

import com.example.chocokcakeV2.domain.auth.presentation.dto.request.GeneralSignUpRequest
import com.example.chocokcakeV2.domain.auth.domain.General
import com.example.chocokcakeV2.domain.auth.domain.User
import com.example.chocokcakeV2.domain.auth.domain.type.Role
import com.example.chocokcakeV2.domain.auth.repository.UserRepository
import com.example.chocokcakeV2.global.error.exception.DuplicatedMemberException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthServiceImpl(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val generalRepository: UserRepository<General>,
    private val userRepository: UserRepository<User>
): AuthService{
    override fun generalSignUp(request: GeneralSignUpRequest) {
        if(userRepository.existsByAccountId(request.accountId)){
            generalRepository.save(General(
                id = null,
                name = request.name,
                accountId = request.accountId,
                password = passwordEncoder.encode(request.password),
                birthDay = request.birthDay,
                createdAt = LocalDateTime.now(),
                updatedAt = null,
                role = Role.GENERAL
            ))
        }else throw DuplicatedMemberException(request.accountId)

    }
}