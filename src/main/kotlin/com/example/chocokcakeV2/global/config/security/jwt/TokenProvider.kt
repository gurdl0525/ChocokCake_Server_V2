package com.example.chocokcakeV2.global.config.security.jwt

import com.example.chocokcakeV2.global.config.security.jwt.dotenv.TokenProperty
import com.example.chocokcakeV2.global.error.exception.TokenCanNotBeNullException
import com.example.chocokcakeV2.global.error.exception.UnAuthorizedException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class TokenProvider(
    private val property: TokenProperty
) {
    fun generateAccessToken(accountId: String): String{
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, property.secretKey)
            .setHeaderParam("header", property.header)
            .claim("type", property.accessTyp)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + (property.accessExp * 1000)))
            .compact()
    }

    fun generateRefreshToken(accountId: String): String{
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, property.secretKey)
            .setHeaderParam("header", property.header)
            .claim("type", property.refreshTyp)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + (property.refreshExp * 1000)))
            .compact()
    }

    fun solveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(property.header)
            ?: return null
        return if (bearerToken.length > 7) {
            bearerToken.substring(7)
        } else throw UnAuthorizedException(request.toString())
    }

    fun validatedToken(token: String): String?{
        if(getTokenBody(token).expiration.after(Date())) {
            return getSubject(token)
        } else throw UnAuthorizedException(token)
    }

    fun getSubject(token: String): String {
        return getTokenBody(token).subject
            ?:throw TokenCanNotBeNullException("Subject is Null")
    }

    private fun getTokenBody(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(property.secretKey).parseClaimsJws(token).body;
    }
}