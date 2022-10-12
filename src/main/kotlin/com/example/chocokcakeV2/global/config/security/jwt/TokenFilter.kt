package com.example.chocokcakeV2.global.config.security.jwt

import com.example.chocokcakeV2.global.config.security.auth.AuthDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenFilter(
    private val tokenProvider: TokenProvider,
    private val authDetailsService: AuthDetailsService
): OncePerRequestFilter() {

    @Throws(Exception::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val subject: String? = tokenProvider.solveToken(request)
            ?.let{
                return@let tokenProvider.validatedToken(it)
            }
        subject?.let {
            val userDetails = authDetailsService.loadUserByUsername(it)
            UsernamePasswordAuthenticationToken(userDetails, "", null)
        }
        filterChain.doFilter(request, response)
    }
}