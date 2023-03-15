package com.example.chocokcakeV2.global.config.security.jwt

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenFilter(
    private val tokenProvider: TokenProvider,
): OncePerRequestFilter() {

    @Throws(Exception::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        tokenProvider.solveToken(request)?.let {
            tokenProvider.validatedToken(it)
        }?.let {
            SecurityContextHolder.getContext().authentication = tokenProvider.getAuthentication(it)
        }
        filterChain.doFilter(request, response)
    }
}