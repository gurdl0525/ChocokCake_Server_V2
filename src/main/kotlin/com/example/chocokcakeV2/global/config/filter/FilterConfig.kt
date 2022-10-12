package com.example.chocokcakeV2.global.config.filter

import com.example.chocokcakeV2.global.config.security.auth.AuthDetailsService
import com.example.chocokcakeV2.global.config.security.jwt.TokenFilter
import com.example.chocokcakeV2.global.config.security.jwt.TokenProvider
import com.example.chocokcakeV2.global.error.handler.ExceptionHandlerFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val tokenProvider: TokenProvider,
    private val authDetailsService: AuthDetailsService,
    private val exceptionHandlerFilter: ExceptionHandlerFilter
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity) {
        val tokenFilter = TokenFilter(tokenProvider, authDetailsService)
        builder.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(exceptionHandlerFilter, TokenFilter::class.java)
    }
}