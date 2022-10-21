package com.example.chocokcakeV2.domain.auth.repository

import com.example.chocokcakeV2.domain.auth.entity.user.General
import org.springframework.data.jpa.repository.JpaRepository

interface GeneralRepository: JpaRepository<General, Long> {
}