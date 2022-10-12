package com.example.chocokcakeV2.domain.auth.service

import com.example.chocokcakeV2.domain.auth.domain.Admin
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.GeneralSignUpRequest
import com.example.chocokcakeV2.domain.auth.domain.General
import com.example.chocokcakeV2.domain.auth.domain.User
import com.example.chocokcakeV2.domain.auth.domain.type.Role
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.AdminSignUpRequest
import com.example.chocokcakeV2.domain.auth.repository.UserRepository
import com.example.chocokcakeV2.global.config.security.auth.AuthenticationFacade
import com.example.chocokcakeV2.domain.auth.exception.DuplicatedMemberException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthServiceImpl(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val generalRepository: UserRepository<General>,
    private val adminRepository: UserRepository<Admin>,
    private val userRepository: UserRepository<User>,
    private val authenticationFacade: AuthenticationFacade
): AuthService{
    override fun generalSignUp(request: GeneralSignUpRequest) {
        duplicateMemberVerification(request.accountId)
        generalRepository.save(General(
            id = null,
            name = request.name,
            accountId = request.accountId,
            password = passwordEncoder.encode(request.password),
            birthDay = authenticationFacade.getBirthDay(request.monthOfBirthDay, request.dayOfBirthDay),
            createdAt = LocalDateTime.now(),
            role = Role.GENERAL
        ))
    }

    override fun admin(request: AdminSignUpRequest) {
        duplicateMemberVerification(request.accountId)
        adminRepository.save(Admin(
            id = null,
            name = request.name,
            accountId = request.accountId,
            password = passwordEncoder.encode(request.password),
            createdAt = LocalDateTime.now()
        ))
    }
    private fun duplicateMemberVerification(accountId: String){
        if(userRepository.existsByAccountId(accountId)){
            throw DuplicatedMemberException(accountId)
        }
    }
}