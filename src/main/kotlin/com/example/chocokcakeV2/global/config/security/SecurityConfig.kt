package com.example.chocokcakeV2.global.config.security

import com.example.chocokcakeV2.global.config.filter.FilterConfig
import com.example.chocokcakeV2.global.config.security.jwt.TokenProvider
import com.example.chocokcakeV2.global.error.handler.ExceptionHandlerFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val exceptionHandlerFilter: ExceptionHandlerFilter
) {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .formLogin().disable()
            .cors()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()

            .antMatchers(HttpMethod.GET, "/user/info/{id}").authenticated()
            .antMatchers(HttpMethod.DELETE, "/auth").authenticated()
            .anyRequest().permitAll()

            .and()
            .apply(FilterConfig(tokenProvider, exceptionHandlerFilter))

            .and().build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}