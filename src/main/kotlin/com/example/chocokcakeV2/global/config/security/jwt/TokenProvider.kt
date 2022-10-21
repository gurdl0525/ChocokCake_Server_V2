package com.example.chocokcakeV2.global.config.security.jwt

import com.example.chocokcakeV2.domain.auth.entity.token.RefreshToken
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.TokenResponse
import com.example.chocokcakeV2.domain.auth.repository.RefreshTokenRepository
import com.example.chocokcakeV2.global.config.security.auth.AuthDetailsService
import com.example.chocokcakeV2.global.config.security.jwt.dotenv.TokenProperty
import com.example.chocokcakeV2.global.error.exception.UnAuthorizedException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest


@Component
class TokenProvider(
    private val property: TokenProperty,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val authDetailsService: AuthDetailsService
) {
    fun generateTokens(accountId: String): TokenResponse{
        val redis = refreshTokenRepository.save(
            RefreshToken(
                accountId,
                generateAccessToken(accountId),
                generateRefreshToken(),
                property.refreshExp
            )
        )
        return TokenResponse(
            redis.accessToken,
            redis.refreshToken
        )
    }

    private fun generateAccessToken(accountId: String): String{
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, property.secretKey)
            .setSubject(accountId)
            .setHeaderParam("header", property.header)
            .claim("typ", property.accessTyp)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + (property.accessExp * 1000)))
            .compact()
    }

    private fun generateRefreshToken(): String{
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, property.secretKey)
            .setHeaderParam("header", property.header)
            .claim("typ", property.refreshTyp)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + (property.refreshExp * 1000)))
            .compact()
    }

    fun solveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(property.header)
            ?: null
        if (bearerToken != null && Pattern.matches("Bearer [(a-zA-Z0-9-._~+/=*)]{30,600}", bearerToken)) {
            return bearerToken.substring(7)
        }
        return null
    }

    fun validatedToken(token: String): String{
        if(getTokenBody(token).expiration.after(Date())) {
            return token
        } else throw UnAuthorizedException(token)
    }

    fun getSubject(token: String): String {
        return getTokenBody(token).subject
            ?: throw UnAuthorizedException("Token Subject is Null")
    }

    private fun getTokenBody(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(property.secretKey).parseClaimsJws(token).body
    }

    fun getAuthentication(token: String): Authentication {
        val authDetails = authDetailsService.loadUserByUsername(getSubject(token))
        return UsernamePasswordAuthenticationToken(authDetails, "", authDetails.authorities)
    }
}