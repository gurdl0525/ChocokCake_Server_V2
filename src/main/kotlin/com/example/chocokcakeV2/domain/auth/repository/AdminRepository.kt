package com.example.chocokcakeV2.domain.auth.repository

import com.example.chocokcakeV2.domain.auth.entity.user.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository: JpaRepository<Admin, Long> {
}