package com.example.chocokcakeV2.domain.cake.repository

import com.example.chocokcakeV2.domain.auth.entity.user.General
import org.springframework.data.jpa.repository.JpaRepository

interface CakeRepository: JpaRepository<General, Long> {
}