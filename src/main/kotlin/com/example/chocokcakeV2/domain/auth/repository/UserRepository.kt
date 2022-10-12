package com.example.chocokcakeV2.domain.auth.repository

import com.example.chocokcakeV2.domain.auth.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository<T: User>: JpaRepository<T, Long> {
    fun existsByAccountId(accountId: String): Boolean

    fun findByAccountId(accountId: String): Optional<User>
}