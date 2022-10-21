package com.example.chocokcakeV2.global.config.security.auth

import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.auth.repository.UserRepository
import com.example.chocokcakeV2.global.error.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService(
    private val userRepository: UserRepository<User>
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByAccountId(username)
            //.map { AuthDetails(it) }
            .orElseThrow { UserNotFoundException("$username to token") }
    }

}