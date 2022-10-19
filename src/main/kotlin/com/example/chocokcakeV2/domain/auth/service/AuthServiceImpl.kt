package com.example.chocokcakeV2.domain.auth.service

import com.example.chocokcakeV2.domain.auth.entity.token.RefreshToken
import com.example.chocokcakeV2.domain.auth.entity.user.Admin
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.GeneralSignUpRequest
import com.example.chocokcakeV2.domain.auth.entity.user.General
import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.auth.entity.user.type.Role
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.AdminSignUpRequest
import com.example.chocokcakeV2.domain.auth.repository.UserRepository
import com.example.chocokcakeV2.global.config.security.auth.AuthenticationFacade
import com.example.chocokcakeV2.domain.auth.exception.DuplicatedMemberException
import com.example.chocokcakeV2.domain.auth.exception.IncorrectPasswordException
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.LoginRequest
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.TokenResponse
import com.example.chocokcakeV2.domain.auth.repository.RefreshTokenRepository
import com.example.chocokcakeV2.global.config.security.jwt.TokenProvider
import com.example.chocokcakeV2.global.config.security.jwt.dotenv.TokenProperty
import com.example.chocokcakeV2.global.error.exception.UserNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthServiceImpl(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val generalRepository: UserRepository<General>,
    private val adminRepository: UserRepository<Admin>,
    private val userRepository: UserRepository<User>,
    private val authenticationFacade: AuthenticationFacade,
    private val tokenProvider: TokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenProperty: TokenProperty
): AuthService{
    override fun generalSignUp(request: GeneralSignUpRequest) {
        duplicateMemberVerification(request.accountId)
        generalRepository.save(
            General(
            id = null,
            name = request.name,
            accountId = request.accountId,
            password = passwordEncoder.encode(request.password),
            birthDay = authenticationFacade.getBirthDay(request.monthOfBirthDay, request.dayOfBirthDay),
            createdAt = LocalDateTime.now(),
            role = Role.GENERAL
            )
        )
    }

    override fun admin(request: AdminSignUpRequest) {
        duplicateMemberVerification(request.accountId)
        adminRepository.save(
            Admin(
            id = null,
            name = request.name,
            accountId = request.accountId,
            password = passwordEncoder.encode(request.password),
            createdAt = LocalDateTime.now()
            )
        )
    }

    private fun duplicateMemberVerification(accountId: String){
        if(userRepository.existsByAccountId(accountId)){
            throw DuplicatedMemberException(accountId)
        }
    }

    override fun login(request: LoginRequest): TokenResponse {

        val user = userRepository.findByAccountId(request.accountId)
            .orElseThrow{UserNotFoundException(request.accountId)}

        if(passwordEncoder.matches(request.password, user.password)){
            val response = tokenProvider.generateTokens(user.accountId)
            refreshTokenRepository.save(RefreshToken(
                id = user.accountId,
                accessToken = response.accessToken,
                refreshToken = response.refreshToken,
                tokenProperty.refreshExp
            ))
        }
        throw IncorrectPasswordException(request.password)
    }
}