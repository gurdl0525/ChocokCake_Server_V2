package com.example.chocokcakeV2.domain.auth.service

import com.example.chocokcakeV2.domain.auth.entity.user.Admin
import com.example.chocokcakeV2.domain.auth.entity.user.General
import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.auth.entity.user.type.Role
import com.example.chocokcakeV2.domain.auth.exception.DuplicatedMemberException
import com.example.chocokcakeV2.domain.auth.exception.IncorrectPasswordException
import com.example.chocokcakeV2.domain.auth.presentation.dto.request.*
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.TokenResponse
import com.example.chocokcakeV2.domain.auth.repository.AdminRepository
import com.example.chocokcakeV2.domain.auth.repository.GeneralRepository
import com.example.chocokcakeV2.domain.auth.repository.RefreshTokenRepository
import com.example.chocokcakeV2.domain.auth.repository.UserRepository
import com.example.chocokcakeV2.global.common.facade.BirthDayFacade
import com.example.chocokcakeV2.global.config.security.jwt.TokenProvider
import com.example.chocokcakeV2.global.error.exception.UserNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthServiceImpl(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val generalRepository: GeneralRepository,
    private val adminRepository: AdminRepository,
    private val userRepository: UserRepository<User>,
    private val tokenProvider: TokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val birthDayFacade: BirthDayFacade
): AuthService{

    override fun checkDuplicateAccountId(request: String) {
        duplicateMemberVerification(request)
    }

    override fun generalSignUp(request: GeneralSignUpRequest) {
        duplicateMemberVerification(request.accountId)
        generalRepository.save(
            General(
            id = null,
            name = request.name,
            accountId = request.accountId,
            password = passwordEncoder.encode(request.password),
            birthDay = birthDayFacade.enterBirthDay(request.monthOfBirthDay, request.dayOfBirthDay),
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
            return tokenProvider.generateTokens(user.accountId)
        }
        throw IncorrectPasswordException(request.password)
    }

    override fun reissue(request: ReissueTokenRequest): TokenResponse {

        val redis = refreshTokenRepository
            .findByAccessTokenAndRefreshToken(request.accessToken, request.refreshToken).orElse(null)

        val accountId = tokenProvider.getSubject(redis.accessToken)

        if(redis != null && userRepository.existsByAccountId(accountId)){
            refreshTokenRepository.delete(redis)
            return tokenProvider.generateTokens(accountId)
        }
        else throw UserNotFoundException("User Not Found By Tokens : $request")
    }

    override fun deleteMember(user: User, request: WithdrawalRequest) {
        if(passwordEncoder.matches(request.password, user.password)){
            userRepository.delete(user)
        }
        else throw IncorrectPasswordException(request.password)
    }
}